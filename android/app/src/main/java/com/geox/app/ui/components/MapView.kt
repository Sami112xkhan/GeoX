package com.geox.app.ui.components

import android.graphics.Canvas
import android.graphics.Paint
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView as OsmMapView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.geox.app.data.DisasterData
import android.view.MotionEvent

@Composable
fun MapView(
    points: List<DisasterData>,
    userLocation: Pair<Double, Double>? = null,
    selectedDisaster: DisasterData? = null,
    onDisasterClick: ((DisasterData) -> Unit)? = null,
    onVisibleDisastersChanged: ((List<DisasterData>) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val disasterOverlay = remember { ClickableDisasterOverlay(emptyList(), onDisasterClick) }
    val userLocationOverlay = remember { UserLocationOverlay(null) }
    var mapRef by remember { mutableStateOf<OsmMapView?>(null) }
    var hasCenteredOnUser by remember { mutableStateOf(false) }
    var lastSelectedDisasterId by remember { mutableStateOf<String?>(null) }
    val visibleCallback = rememberUpdatedState(onVisibleDisastersChanged)

    AndroidView(
        modifier = modifier,
        factory = {
            Configuration.getInstance().userAgentValue = it.packageName
            OsmMapView(it).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                isHorizontalMapRepetitionEnabled = false
                isVerticalMapRepetitionEnabled = false
                mapRef = this
                
                // Add map listener to track visible disasters
                val mapListener = object : MapListener {
                    override fun onScroll(event: ScrollEvent?): Boolean {
                        updateVisibleDisasters(disasterOverlay, visibleCallback.value)
                        return false
                    }
                    
                    override fun onZoom(event: ZoomEvent?): Boolean {
                        updateVisibleDisasters(disasterOverlay, visibleCallback.value)
                        return false
                    }
                    
                    private fun updateVisibleDisasters(
                        overlay: ClickableDisasterOverlay,
                        callback: ((List<DisasterData>) -> Unit)?
                    ) {
                        try {
                            val boundingBox = boundingBox ?: return
                            val visible = overlay.points.filter { disaster ->
                                val lat = disaster.coordinates.first
                                val lon = disaster.coordinates.second
                                lat >= boundingBox.latSouth && lat <= boundingBox.latNorth &&
                                lon >= boundingBox.lonWest && lon <= boundingBox.lonEast
                            }
                            callback?.invoke(visible)
                        } catch (_: Exception) {
                        }
                    }
                }
                addMapListener(mapListener)
                
                // Initial update
                post {
                    try {
                        val boundingBox = boundingBox
                        if (boundingBox != null) {
                            val visible = disasterOverlay.points.filter { disaster ->
                                val lat = disaster.coordinates.first
                                val lon = disaster.coordinates.second
                                lat >= boundingBox.latSouth && lat <= boundingBox.latNorth &&
                                lon >= boundingBox.lonWest && lon <= boundingBox.lonEast
                            }
                            visibleCallback.value?.invoke(visible)
                        }
                    } catch (e: Exception) {
                        // Ignore
                    }
                }
                
                // Center on user location if available, otherwise center on disasters
                if (userLocation != null) {
                    controller.setZoom(6.0)
                    controller.setCenter(GeoPoint(userLocation.first, userLocation.second))
                } else if (points.isNotEmpty()) {
                    val avgLat = points.map { it.coordinates.first }.average()
                    val avgLon = points.map { it.coordinates.second }.average()
                    controller.setZoom(3.0)
                    controller.setCenter(GeoPoint(avgLat, avgLon))
                } else {
                    controller.setZoom(2.5)
                    controller.setCenter(GeoPoint(20.0, 0.0))
                }
                
                overlays.add(disasterOverlay)
                overlays.add(userLocationOverlay)
            }
        },
        update = { map ->
            disasterOverlay.points = points
            disasterOverlay.onDisasterClick = onDisasterClick
            userLocationOverlay.userLocation = userLocation
            
            // Center on selected disaster if provided
            selectedDisaster?.let { disaster ->
                if (lastSelectedDisasterId != disaster.id) {
                    lastSelectedDisasterId = disaster.id
                    val geoPoint = GeoPoint(disaster.coordinates.first, disaster.coordinates.second)
                    map.controller.setZoom(8.0)
                    map.controller.animateTo(geoPoint)
                }
            } ?: run {
                lastSelectedDisasterId = null
            }
            
            if (!hasCenteredOnUser && userLocation != null) {
                hasCenteredOnUser = true
                val geoPoint = GeoPoint(userLocation.first, userLocation.second)
                map.controller.setZoom(6.0)
                map.controller.animateTo(geoPoint)
            }
            
            map.post {
                updateVisibleDisasters(map, disasterOverlay, visibleCallback.value)
            }
            
            map.invalidate()
        }
    )
}

private class UserLocationOverlay(initialLocation: Pair<Double, Double>?) : Overlay() {
    var userLocation: Pair<Double, Double>? = initialLocation
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = android.graphics.Color.rgb(66, 133, 244) // Google Maps blue
    }
    private val outerPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = android.graphics.Color.argb(30, 66, 133, 244)
    }
    private val borderPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = android.graphics.Color.WHITE
        strokeWidth = 4f
    }
    
    override fun draw(c: Canvas, osmv: org.osmdroid.views.MapView, shadow: Boolean) {
        if (shadow || userLocation == null) return
        val loc = userLocation ?: return
        val pj = osmv.projection
        val p = pj.toPixels(GeoPoint(loc.first, loc.second), null)
        
        // Always draw location marker (even if slightly off-screen for smooth transitions)
        // Outer pulse ring (like Google Maps)
        c.drawCircle(p.x.toFloat(), p.y.toFloat(), 40f, outerPaint)
        // White border
        c.drawCircle(p.x.toFloat(), p.y.toFloat(), 18f, borderPaint)
        // Inner dot
        c.drawCircle(p.x.toFloat(), p.y.toFloat(), 14f, paint)
    }
}

private fun updateVisibleDisasters(
    map: OsmMapView,
    overlay: ClickableDisasterOverlay,
    callback: ((List<DisasterData>) -> Unit)?
) {
    try {
        val boundingBox = map.boundingBox ?: return
        val visible = overlay.points.filter { disaster ->
            val lat = disaster.coordinates.first
            val lon = disaster.coordinates.second
            lat >= boundingBox.latSouth && lat <= boundingBox.latNorth &&
            lon >= boundingBox.lonWest && lon <= boundingBox.lonEast
        }
        callback?.invoke(visible)
    } catch (_: Exception) {
    }
}

private class ClickableDisasterOverlay(
    initialPoints: List<DisasterData>,
    var onDisasterClick: ((DisasterData) -> Unit)?
) : Overlay() {
    var points: List<DisasterData> = initialPoints
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val selectedPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 6f
        color = android.graphics.Color.rgb(255, 215, 0) // Gold for selected
    }
    
    override fun draw(c: Canvas, osmv: org.osmdroid.views.MapView, shadow: Boolean) {
        if (shadow) return
        val pj = osmv.projection
        points.forEach { d ->
            val coords = d.coordinates
            val p = pj.toPixels(GeoPoint(coords.first, coords.second), null)
            val intensity = (d.magnitude ?: 2.0).toFloat().coerceIn(1f, 10f)
            val radius = intensity * 8f
            
            // Heat effect
            paint.color = android.graphics.Color.argb((20 * intensity).toInt().coerceIn(30, 180), 11, 87, 164)
            c.drawCircle(p.x.toFloat(), p.y.toFloat(), radius * 2.2f, paint)
            paint.color = android.graphics.Color.argb((40 * intensity).toInt().coerceIn(50, 220), 11, 87, 164)
            c.drawCircle(p.x.toFloat(), p.y.toFloat(), radius, paint)
            
            // Center marker
            paint.color = when (d.type) {
                com.geox.app.data.DisasterType.EARTHQUAKE -> android.graphics.Color.rgb(255, 0, 0)
                com.geox.app.data.DisasterType.WILDFIRE -> android.graphics.Color.rgb(255, 140, 0)
                com.geox.app.data.DisasterType.VOLCANO -> android.graphics.Color.rgb(148, 0, 211)
                com.geox.app.data.DisasterType.FLOOD -> android.graphics.Color.rgb(0, 100, 255)
                com.geox.app.data.DisasterType.STORM -> android.graphics.Color.rgb(0, 191, 255)
            }
            c.drawCircle(p.x.toFloat(), p.y.toFloat(), 12f, paint)
            
            // White border
            paint.color = android.graphics.Color.WHITE
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 3f
            c.drawCircle(p.x.toFloat(), p.y.toFloat(), 12f, paint)
            paint.style = Paint.Style.FILL
        }
    }
    
    override fun onSingleTapConfirmed(e: MotionEvent?, mapView: OsmMapView?): Boolean {
        if (e == null || mapView == null || onDisasterClick == null) return false
        
        val pj = mapView.projection
        val tapPoint = GeoPoint(pj.fromPixels(e.x.toInt(), e.y.toInt()))
        
        // Find nearest disaster within 50 pixels
        var nearest: DisasterData? = null
        var minDistance = Float.MAX_VALUE
        
        points.forEach { disaster ->
            val disasterPoint = GeoPoint(disaster.coordinates.first, disaster.coordinates.second)
            val screenPoint = pj.toPixels(disasterPoint, null)
            val dx = screenPoint.x - e.x
            val dy = screenPoint.y - e.y
            val distance = kotlin.math.sqrt(dx * dx + dy * dy)
            
            if (distance < 50f && distance < minDistance) {
                minDistance = distance
                nearest = disaster
            }
        }
        
        nearest?.let { onDisasterClick?.invoke(it) }
        return nearest != null
    }
}


