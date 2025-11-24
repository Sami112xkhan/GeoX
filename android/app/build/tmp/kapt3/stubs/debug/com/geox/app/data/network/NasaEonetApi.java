package com.geox.app.data.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ \u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J8\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\n2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/geox/app/data/network/NasaEonetApi;", "", "getCategories", "", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEvents", "Lcom/geox/app/data/model/EonetResponse;", "limit", "", "days", "category", "status", "(IILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface NasaEonetApi {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://eonet.gsfc.nasa.gov/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WILDFIRE = "wildfires";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STORM = "seaLakeIce";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VOLCANO = "volcanoes";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FLOOD = "floods";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DROUGHT = "drought";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DUST_HAZE = "dustHaze";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SNOW = "snow";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LANDSLIDE = "landslides";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MANMADE = "manmade";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEA_LAKE_ICE = "seaLakeIce";
    @org.jetbrains.annotations.NotNull()
    public static final com.geox.app.data.network.NasaEonetApi.Companion Companion = null;
    
    @retrofit2.http.GET(value = "api/v3/events")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEvents(@retrofit2.http.Query(value = "limit")
    int limit, @retrofit2.http.Query(value = "days")
    int days, @retrofit2.http.Query(value = "category")
    @org.jetbrains.annotations.Nullable()
    java.lang.String category, @retrofit2.http.Query(value = "status")
    @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.geox.app.data.model.EonetResponse> $completion);
    
    @retrofit2.http.GET(value = "api/v3/categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/geox/app/data/network/NasaEonetApi$Companion;", "", "()V", "BASE_URL", "", "DROUGHT", "DUST_HAZE", "FLOOD", "LANDSLIDE", "MANMADE", "SEA_LAKE_ICE", "SNOW", "STORM", "VOLCANO", "WILDFIRE", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://eonet.gsfc.nasa.gov/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String WILDFIRE = "wildfires";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String STORM = "seaLakeIce";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String VOLCANO = "volcanoes";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String FLOOD = "floods";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DROUGHT = "drought";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DUST_HAZE = "dustHaze";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SNOW = "snow";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String LANDSLIDE = "landslides";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String MANMADE = "manmade";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SEA_LAKE_ICE = "seaLakeIce";
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}