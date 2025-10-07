package com.samikhan.geox.di

import android.content.Context
import androidx.room.Room
import com.samikhan.geox.data.local.AppDatabase
import com.samikhan.geox.data.network.NasaEonetApi
import com.samikhan.geox.data.network.UsgsApi
import com.samikhan.geox.data.prefs.UserPreferences
import com.samikhan.geox.data.repository.EarthquakeRepository
import com.samikhan.geox.data.repository.DisasterRepository
import com.samikhan.geox.data.repository.impl.FakeEarthquakeRepository
import com.samikhan.geox.data.repository.impl.NetworkDisasterRepository
import com.samikhan.geox.data.repository.impl.NetworkEarthquakeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {
    @Volatile private var earthquakeRepositoryInstance: EarthquakeRepository? = null
    @Volatile private var disasterRepositoryInstance: DisasterRepository? = null

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

    fun provideFakeEarthquakeRepository(): EarthquakeRepository {
        return FakeEarthquakeRepository()
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

    private fun provideRetrofit(baseUrl: String): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder().addInterceptor(logger).build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}


