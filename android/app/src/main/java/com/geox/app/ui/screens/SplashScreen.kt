package com.geox.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geox.app.theme.*
import kotlin.math.sin
import kotlin.math.cos

@Composable
fun SplashScreen(
    onGetStarted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Green50,
                        Yellow50,
                        Blue50
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated gradient orbs in background
        AnimatedOrbs()

        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            // 3D Liquid Glass Sphere
            val infiniteTransition = rememberInfiniteTransition(label = "sphere")
            val floatAnimation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(4000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "float"
            )

            Box(
                modifier = Modifier
                    .size(192.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.95f),
                                Color.White.copy(alpha = 0.75f)
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Lime accent blob inside
                val scaleAnimation by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.2f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(3000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "scale"
                )

                Box(
                    modifier = Modifier
                        .size((80 * scaleAnimation).dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    LimeGreen.copy(alpha = 0.6f),
                                    LimeGreen.copy(alpha = 0.2f)
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }

            // Title
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "GeoX",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    letterSpacing = (-2.16).sp
                )
                Text(
                    text = "Disaster Intelligence. Simplified.",
                    fontSize = 18.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
            }

            // Get Started button
            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GlassWhite,
                    contentColor = TextPrimary
                ),
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 32.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun AnimatedOrbs() {
    val infiniteTransition = rememberInfiniteTransition(label = "orbs")
    
    val orb1X by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb1X"
    )
    
    val orb1Y by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "orb1Y"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Orb 1
        Box(
            modifier = Modifier
                .offset(
                    x = (orb1X * 150 - 75).dp,
                    y = (orb1Y * 100 - 50).dp
                )
                .size(500.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            LimeGreen.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Orb 2
        val orb2X by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(25000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "orb2X"
        )
        
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(
                    x = (orb2X * 80 - 40).dp,
                    y = (orb2X * 80 - 40).dp
                )
                .size(400.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Blue50.copy(alpha = 0.25f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Orb 3
        val orb3X by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(22000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "orb3X"
        )
        
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = (orb3X * 60 - 30).dp,
                    y = (orb3X * 100 - 50).dp
                )
                .size(350.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Purple50.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
    }
}

