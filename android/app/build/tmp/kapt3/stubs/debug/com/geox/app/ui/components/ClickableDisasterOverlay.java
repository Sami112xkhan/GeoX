package com.geox.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\bJ \u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001c\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0019H\u0016R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/geox/app/ui/components/ClickableDisasterOverlay;", "Lorg/osmdroid/views/overlay/Overlay;", "initialPoints", "", "Lcom/geox/app/data/DisasterData;", "onDisasterClick", "Lkotlin/Function1;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getOnDisasterClick", "()Lkotlin/jvm/functions/Function1;", "setOnDisasterClick", "(Lkotlin/jvm/functions/Function1;)V", "paint", "Landroid/graphics/Paint;", "points", "getPoints", "()Ljava/util/List;", "setPoints", "(Ljava/util/List;)V", "selectedPaint", "draw", "c", "Landroid/graphics/Canvas;", "osmv", "Lorg/osmdroid/views/MapView;", "shadow", "", "onSingleTapConfirmed", "e", "Landroid/view/MotionEvent;", "mapView", "app_debug"})
final class ClickableDisasterOverlay extends org.osmdroid.views.overlay.Overlay {
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.geox.app.data.DisasterData, kotlin.Unit> onDisasterClick;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.geox.app.data.DisasterData> points;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint paint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint selectedPaint = null;
    
    public ClickableDisasterOverlay(@org.jetbrains.annotations.NotNull()
    java.util.List<com.geox.app.data.DisasterData> initialPoints, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.geox.app.data.DisasterData, kotlin.Unit> onDisasterClick) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<com.geox.app.data.DisasterData, kotlin.Unit> getOnDisasterClick() {
        return null;
    }
    
    public final void setOnDisasterClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.geox.app.data.DisasterData, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.geox.app.data.DisasterData> getPoints() {
        return null;
    }
    
    public final void setPoints(@org.jetbrains.annotations.NotNull()
    java.util.List<com.geox.app.data.DisasterData> p0) {
    }
    
    @java.lang.Override()
    public void draw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    org.osmdroid.views.MapView osmv, boolean shadow) {
    }
    
    @java.lang.Override()
    public boolean onSingleTapConfirmed(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent e, @org.jetbrains.annotations.Nullable()
    org.osmdroid.views.MapView mapView) {
        return false;
    }
}