package com.geox.app.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/geox/app/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "disasterEventDao", "Lcom/geox/app/data/local/DisasterEventDao;", "earthquakeEventDao", "Lcom/geox/app/data/local/EarthquakeEventDao;", "app_debug"})
@androidx.room.Database(entities = {com.geox.app.data.model.EarthquakeEvent.class, com.geox.app.data.model.DisasterEvent.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.geox.app.data.local.EarthquakeEventDao earthquakeEventDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.geox.app.data.local.DisasterEventDao disasterEventDao();
}