package com.geox.app.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/geox/app/data/network/UsgsApi;", "", "getEarthquakes", "Lcom/geox/app/data/model/UsgsResponse;", "feed", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface UsgsApi {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://earthquake.usgs.gov/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALL_DAY = "all_day";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALL_WEEK = "all_week";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALL_MONTH = "all_month";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNIFICANT_DAY = "significant_day";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNIFICANT_WEEK = "significant_week";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNIFICANT_MONTH = "significant_month";
    @org.jetbrains.annotations.NotNull()
    public static final com.geox.app.data.network.UsgsApi.Companion Companion = null;
    
    @retrofit2.http.GET(value = "earthquakes/feed/v1.0/summary/{feed}.geojson")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEarthquakes(@retrofit2.http.Path(value = "feed")
    @org.jetbrains.annotations.NotNull()
    java.lang.String feed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geox.app.data.model.UsgsResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/geox/app/data/network/UsgsApi$Companion;", "", "()V", "ALL_DAY", "", "ALL_MONTH", "ALL_WEEK", "BASE_URL", "SIGNIFICANT_DAY", "SIGNIFICANT_MONTH", "SIGNIFICANT_WEEK", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://earthquake.usgs.gov/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ALL_DAY = "all_day";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ALL_WEEK = "all_week";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String ALL_MONTH = "all_month";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SIGNIFICANT_DAY = "significant_day";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SIGNIFICANT_WEEK = "significant_week";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SIGNIFICANT_MONTH = "significant_month";
        
        private Companion() {
            super();
        }
    }
}