package com.geox.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u0010H\u0016J\u000e\u0010\u0012\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\f\u0010\u0013\u001a\u00020\u000b*\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/geox/app/data/repository/NetworkEarthquakeRepository;", "Lcom/geox/app/data/repository/EarthquakeRepository;", "usgsApi", "Lcom/geox/app/data/network/UsgsApi;", "earthquakeDao", "Lcom/geox/app/data/local/EarthquakeEventDao;", "(Lcom/geox/app/data/network/UsgsApi;Lcom/geox/app/data/local/EarthquakeEventDao;)V", "clearCache", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEventById", "Lcom/geox/app/data/model/EarthquakeEvent;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentEarthquakes", "Lkotlinx/coroutines/flow/Flow;", "", "refreshEarthquakes", "toEarthquakeEvent", "Lcom/geox/app/data/model/UsgsFeature;", "app_debug"})
public final class NetworkEarthquakeRepository implements com.geox.app.data.repository.EarthquakeRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.geox.app.data.network.UsgsApi usgsApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.geox.app.data.local.EarthquakeEventDao earthquakeDao = null;
    
    public NetworkEarthquakeRepository(@org.jetbrains.annotations.NotNull()
    com.geox.app.data.network.UsgsApi usgsApi, @org.jetbrains.annotations.NotNull()
    com.geox.app.data.local.EarthquakeEventDao earthquakeDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.geox.app.data.model.EarthquakeEvent>> getRecentEarthquakes() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getEventById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geox.app.data.model.EarthquakeEvent> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object refreshEarthquakes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.geox.app.data.model.EarthquakeEvent toEarthquakeEvent(com.geox.app.data.model.UsgsFeature $this$toEarthquakeEvent) {
        return null;
    }
}