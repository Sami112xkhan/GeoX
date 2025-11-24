package com.geox.app.di

import android.content.Context
import androidx.room.Room
import com.geox.app.data.local.AppDatabase
import com.geox.app.data.network.NasaEonetApi
import com.geox.app.data.network.UsgsApi
import com.geox.app.data.network.ComCatApi
import com.geox.app.data.repository.EarthquakeRepository
import com.geox.app.data.repository.DisasterRepository
import com.geox.app.data.repository.NetworkEarthquakeRepository
import com.geox.app.data.repository.NetworkDisasterRepository
import com.geox.app.data.repository.ComCatRepository
import com.geox.app.data.prefs.UserPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object AppModule {
    @Volatile private var earthquakeRepositoryInstance: EarthquakeRepository? = null
    @Volatile private var disasterRepositoryInstance: DisasterRepository? = null
    @Volatile private var comCatRepositoryInstance: ComCatRepository? = null

    fun provideEarthquakeRepository(context: Context): EarthquakeRepository {
        return earthquakeRepositoryInstance ?: synchronized(this) {
            earthquakeRepositoryInstance ?: NetworkEarthquakeRepository(
                usgsApi = provideUsgsApi(),
                earthquakeDao = provideDatabase(context).earthquakeEventDao()
            ).also { earthquakeRepositoryInstance = it }
        }
    }

    fun provideDisasterRepository(context: Context): DisasterRepository {
        return disasterRepositoryInstance ?: synchronized(this) {
            disasterRepositoryInstance ?: NetworkDisasterRepository(
                nasaEonetApi = provideNasaEonetApi(),
                disasterDao = provideDatabase(context).disasterEventDao()
            ).also { disasterRepositoryInstance = it }
        }
    }

    fun provideComCatRepository(context: Context): ComCatRepository {
        return comCatRepositoryInstance ?: synchronized(this) {
            comCatRepositoryInstance ?: ComCatRepository(
                comCatApi = provideComCatApi(),
                earthquakeDao = provideDatabase(context).earthquakeEventDao()
            ).also { comCatRepositoryInstance = it }
        }
    }

    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "geox.db")
            .fallbackToDestructiveMigration()
            .build()

    fun provideUserPreferences(context: Context): UserPreferences = UserPreferences(context)

    private fun provideUsgsApi(): UsgsApi {
        return provideRetrofit(UsgsApi.BASE_URL).create(UsgsApi::class.java)
    }

    private fun provideNasaEonetApi(): NasaEonetApi {
        return provideRetrofit(NasaEonetApi.BASE_URL).create(NasaEonetApi::class.java)
    }

    private fun provideComCatApi(): ComCatApi {
        return provideRetrofit(ComCatApi.BASE_URL).create(ComCatApi::class.java)
    }

    private fun provideRetrofit(baseUrl: String): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder().addInterceptor(logger).build()
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
