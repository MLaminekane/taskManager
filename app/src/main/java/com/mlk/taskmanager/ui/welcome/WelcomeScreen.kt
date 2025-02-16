package com.mlk.taskmanager.ui.welcome

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mlk.taskmanager.R
import com.mlk.taskmanager.ui.navigation.Screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1000)) +
                    slideInVertically(
                        initialOffsetY = { 100 },
                        animationSpec = tween(1000)
                    )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Floating elements animation
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Main illustration
                    Image(
                        painter = painterResource(id = R.drawable.task_illustration),
                        contentDescription = "Task Management Illustration",
                        modifier = Modifier
                            .size(240.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                    
                    // Floating elements
                    FloatingElement(
                        resId = R.drawable.ic_clock,
                        offset = 120,
                        delay = 0,
                        size = 48.dp,
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                    
                    FloatingElement(
                        resId = R.drawable.ic_calendar,
                        offset = -80,
                        delay = 200,
                        size = 40.dp,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    
                    FloatingElement(
                        resId = R.drawable.ic_location,
                        offset = 100,
                        delay = 400,
                        size = 32.dp,
                        modifier = Modifier.align(Alignment.BottomStart)
                    )

                    // Additional floating elements
                    FloatingElement(
                        resId = R.drawable.ic_clock,
                        offset = -60,
                        delay = 600,
                        size = 36.dp,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )

                    FloatingElement(
                        resId = R.drawable.ic_calendar,
                        offset = 90,
                        delay = 800,
                        size = 28.dp,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Task Management &\nTo-Do List",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Tp1 Informatique mobile developpe with Kotlin and Jetpack compose",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = { navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF613BE7)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "TaskManager",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingElement(
    resId: Int,
    offset: Int,
    delay: Int,
    size: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val position by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, delayMillis = delay),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, delayMillis = delay),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = modifier
            .offset(y = (position * offset / 5).dp)
            .size(size)
            .graphicsLayer(rotationZ = rotation)
    )
} 