package com.geox.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\bG\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00c3\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001b\u0012\b\b\u0002\u0010 \u001a\u00020\u0013\u00a2\u0006\u0002\u0010!J\u0010\u0010E\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010F\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010G\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010H\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010I\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010J\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u000b\u0010K\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\t\u0010M\u001a\u00020\u0013H\u00c6\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u0010\u0010O\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010P\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010Q\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010R\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010S\u001a\u0004\u0018\u00010\u001bH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u000b\u0010T\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\u001bH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\t\u0010W\u001a\u00020\u001bH\u00c6\u0003J\t\u0010X\u001a\u00020\u0013H\u00c6\u0003J\u0010\u0010Y\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010[\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010\\\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010]\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010^\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u0010\u0010_\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u00cc\u0002\u0010`\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u001b2\b\b\u0002\u0010 \u001a\u00020\u0013H\u00c6\u0001\u00a2\u0006\u0002\u0010aJ\u0013\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010e\u001a\u00020\u001bH\u00d6\u0001J\t\u0010f\u001a\u00020\u0013H\u00d6\u0001R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010\'R\u0011\u0010\u0015\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\b-\u0010.R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b0\u0010+R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b1\u0010+R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b2\u0010+R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b3\u0010+R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b4\u0010+R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b5\u0010+R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b6\u0010+R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b7\u0010+R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b8\u0010+R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b9\u0010+R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b:\u0010+R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\b;\u0010.R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b<\u0010+R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b=\u0010+R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b>\u0010+R\u0011\u0010\u001f\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0011\u0010 \u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010#R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010#R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010#R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\bD\u0010\'\u00a8\u0006g"}, d2 = {"Lcom/geox/app/data/model/EarthquakeSearchParams;", "", "startTime", "", "endTime", "minLatitude", "", "maxLatitude", "minLongitude", "maxLongitude", "latitude", "longitude", "maxRadiusKm", "maxRadius", "minMagnitude", "maxMagnitude", "minDepth", "maxDepth", "catalog", "", "contributor", "eventType", "alertLevel", "maxMmi", "minCdi", "maxCdi", "minFelt", "", "productType", "reviewStatus", "limit", "offset", "orderBy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;)V", "getAlertLevel", "()Ljava/lang/String;", "getCatalog", "getContributor", "getEndTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEventType", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLimit", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLongitude", "getMaxCdi", "getMaxDepth", "getMaxLatitude", "getMaxLongitude", "getMaxMagnitude", "getMaxMmi", "getMaxRadius", "getMaxRadiusKm", "getMinCdi", "getMinDepth", "getMinFelt", "getMinLatitude", "getMinLongitude", "getMinMagnitude", "getOffset", "()I", "getOrderBy", "getProductType", "getReviewStatus", "getStartTime", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;)Lcom/geox/app/data/model/EarthquakeSearchParams;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class EarthquakeSearchParams {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long startTime = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long endTime = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double minLatitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxLatitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double minLongitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxLongitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double latitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double longitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxRadiusKm = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxRadius = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double minMagnitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxMagnitude = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double minDepth = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxDepth = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String catalog = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String contributor = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String eventType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String alertLevel = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxMmi = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double minCdi = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxCdi = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer minFelt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String productType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String reviewStatus = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer limit = null;
    private final int offset = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String orderBy = null;
    
    public EarthquakeSearchParams(@org.jetbrains.annotations.Nullable()
    java.lang.Long startTime, @org.jetbrains.annotations.Nullable()
    java.lang.Long endTime, @org.jetbrains.annotations.Nullable()
    java.lang.Double minLatitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxLatitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double minLongitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxLongitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxRadiusKm, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxRadius, @org.jetbrains.annotations.Nullable()
    java.lang.Double minMagnitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMagnitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double minDepth, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxDepth, @org.jetbrains.annotations.Nullable()
    java.lang.String catalog, @org.jetbrains.annotations.Nullable()
    java.lang.String contributor, @org.jetbrains.annotations.NotNull()
    java.lang.String eventType, @org.jetbrains.annotations.Nullable()
    java.lang.String alertLevel, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMmi, @org.jetbrains.annotations.Nullable()
    java.lang.Double minCdi, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxCdi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer minFelt, @org.jetbrains.annotations.Nullable()
    java.lang.String productType, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewStatus, @org.jetbrains.annotations.Nullable()
    java.lang.Integer limit, int offset, @org.jetbrains.annotations.NotNull()
    java.lang.String orderBy) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getStartTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getEndTime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMinLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMinLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLongitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxRadiusKm() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxRadius() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMinMagnitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxMagnitude() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMinDepth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxDepth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCatalog() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getContributor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEventType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAlertLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxMmi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMinCdi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxCdi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMinFelt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProductType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReviewStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getLimit() {
        return null;
    }
    
    public final int getOffset() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOrderBy() {
        return null;
    }
    
    public EarthquakeSearchParams() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component25() {
        return null;
    }
    
    public final int component26() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.geox.app.data.model.EarthquakeSearchParams copy(@org.jetbrains.annotations.Nullable()
    java.lang.Long startTime, @org.jetbrains.annotations.Nullable()
    java.lang.Long endTime, @org.jetbrains.annotations.Nullable()
    java.lang.Double minLatitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxLatitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double minLongitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxLongitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double latitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double longitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxRadiusKm, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxRadius, @org.jetbrains.annotations.Nullable()
    java.lang.Double minMagnitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMagnitude, @org.jetbrains.annotations.Nullable()
    java.lang.Double minDepth, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxDepth, @org.jetbrains.annotations.Nullable()
    java.lang.String catalog, @org.jetbrains.annotations.Nullable()
    java.lang.String contributor, @org.jetbrains.annotations.NotNull()
    java.lang.String eventType, @org.jetbrains.annotations.Nullable()
    java.lang.String alertLevel, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMmi, @org.jetbrains.annotations.Nullable()
    java.lang.Double minCdi, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxCdi, @org.jetbrains.annotations.Nullable()
    java.lang.Integer minFelt, @org.jetbrains.annotations.Nullable()
    java.lang.String productType, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewStatus, @org.jetbrains.annotations.Nullable()
    java.lang.Integer limit, int offset, @org.jetbrains.annotations.NotNull()
    java.lang.String orderBy) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}