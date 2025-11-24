package com.geox.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0005\u00a8\u0006\u0016"}, d2 = {"Lcom/geox/app/ui/components/UserLocationOverlay;", "Lorg/osmdroid/views/overlay/Overlay;", "initialLocation", "Lkotlin/Pair;", "", "(Lkotlin/Pair;)V", "borderPaint", "Landroid/graphics/Paint;", "outerPaint", "paint", "userLocation", "getUserLocation", "()Lkotlin/Pair;", "setUserLocation", "draw", "", "c", "Landroid/graphics/Canvas;", "osmv", "Lorg/osmdroid/views/MapView;", "shadow", "", "app_debug"})
final class UserLocationOverlay extends org.osmdroid.views.overlay.Overlay {
    @org.jetbrains.annotations.Nullable()
    private kotlin.Pair<java.lang.Double, java.lang.Double> userLocation;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint paint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint outerPaint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint borderPaint = null;
    
    public UserLocationOverlay(@org.jetbrains.annotations.Nullable()
    kotlin.Pair<java.lang.Double, java.lang.Double> initialLocation) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Pair<java.lang.Double, java.lang.Double> getUserLocation() {
        return null;
    }
    
    public final void setUserLocation(@org.jetbrains.annotations.Nullable()
    kotlin.Pair<java.lang.Double, java.lang.Double> p0) {
    }
    
    @java.lang.Override()
    public void draw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    org.osmdroid.views.MapView osmv, boolean shadow) {
    }
}