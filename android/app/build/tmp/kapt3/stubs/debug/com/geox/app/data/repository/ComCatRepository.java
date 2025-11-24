package com.geox.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00130\u0012J\u000e\u0010\u0014\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\f\u0010\u0019\u001a\u00020\r*\u00020\u001aH\u0002J\f\u0010\u0019\u001a\u00020\r*\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/geox/app/data/repository/ComCatRepository;", "", "comCatApi", "Lcom/geox/app/data/network/ComCatApi;", "earthquakeDao", "Lcom/geox/app/data/local/EarthquakeEventDao;", "(Lcom/geox/app/data/network/ComCatApi;Lcom/geox/app/data/local/EarthquakeEventDao;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "clearCache", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEventById", "Lcom/geox/app/data/model/EarthquakeEvent;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentEarthquakes", "Lkotlinx/coroutines/flow/Flow;", "", "refreshEarthquakes", "searchEarthquakes", "params", "Lcom/geox/app/data/model/EarthquakeSearchParams;", "(Lcom/geox/app/data/model/EarthquakeSearchParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toEarthquakeEvent", "Lcom/geox/app/data/model/ComCatDetailResponse;", "Lcom/geox/app/data/model/ComCatFeature;", "app_debug"})
public final class ComCatRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.geox.app.data.network.ComCatApi comCatApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.geox.app.data.local.EarthquakeEventDao earthquakeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public ComCatRepository(@org.jetbrains.annotations.NotNull()
    com.geox.app.data.network.ComCatApi comCatApi, @org.jetbrains.annotations.NotNull()
    com.geox.app.data.local.EarthquakeEventDao earthquakeDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.geox.app.data.model.EarthquakeEvent>> getRecentEarthquakes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEventById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geox.app.data.model.EarthquakeEvent> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchEarthquakes(@org.jetbrains.annotations.NotNull()
    com.geox.app.data.model.EarthquakeSearchParams params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.geox.app.data.model.EarthquakeEvent>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshEarthquakes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.geox.app.data.model.EarthquakeEvent toEarthquakeEvent(com.geox.app.data.model.ComCatFeature $this$toEarthquakeEvent) {
        return null;
    }
    
    private final com.geox.app.data.model.EarthquakeEvent toEarthquakeEvent(com.geox.app.data.model.ComCatDetailResponse $this$toEarthquakeEvent) {
        return null;
    }
}