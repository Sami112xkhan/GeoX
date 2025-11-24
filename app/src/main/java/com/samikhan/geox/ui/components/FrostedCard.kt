package com.samikhan.geox.ui.components

import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FrostedCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit = {}
) {
    val corner = 24.dp
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(corner),
        onClick = onClick ?: {}
    ) {
        val base = MaterialTheme.colorScheme.surface.copy(alpha = 0.25f)
        Box(
            modifier = Modifier
                .graphicsLayer {
                    // Fallback without RenderEffect to avoid API dependency issues
                    shadowElevation = 12f
                    shape = RoundedCornerShape(corner)
                    clip = true
                }
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            base,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.18f)
                        )
                    )
                )
        ) {
            androidx.compose.foundation.layout.Column(Modifier.padding(16.dp)) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                subtitle?.let { Text(it, style = MaterialTheme.typography.bodyMedium, color = Color.LightGray) }
                androidx.compose.foundation.layout.Spacer(Modifier.padding(6.dp))
                content()
            }
        }
    }
}
