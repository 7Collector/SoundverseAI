package seven.collector.soundverseai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import seven.collector.soundverseai.R
import seven.collector.soundverseai.utilities.VariableFont
import seven.collector.soundverseai.utilities.showNotification

@Composable
fun HomeScreen(
    onNotificationClick: () -> Unit,
    onStartClick: () -> Unit,
) {

    val context = LocalContext.current.applicationContext
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val chats = listOf("Chat 1", "Chat 2")

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text( fontFamily = VariableFont,
                    text = "Soundverse AI",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                NavigationDrawerItem(
                    label = { Text( fontFamily = VariableFont, text = "Home") },
                    selected = true,
                    onClick = { scope.launch { drawerState.close() } })
                NavigationDrawerItem(
                    label = { Text( fontFamily = VariableFont, text = "Profile") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } })
                NavigationDrawerItem(
                    label = { Text( fontFamily = VariableFont, text = "Notification Test") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            delay(10_000L)
                            showNotification(context)
                            drawerState.close()
                        }
                    })
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF9164FF).copy(alpha = 0.48f),
                            Color.Black.copy(alpha = 0.84f)
                        ),
                        start = Offset(0f, -LocalConfiguration.current.screenHeightDp * 0.3f),
                        end = Offset(0f, LocalConfiguration.current.screenHeightDp * 0.5f)
                    )
                )
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { scope.launch { drawerState.open() } },
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF030303), CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier
                                .size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = onNotificationClick,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Black)
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.weight(1.0f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_on_black),
                        contentDescription = "Logo",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text( fontFamily = VariableFont,
                        text = "Welcome to Soundverse AI",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF6A35EE),
                                        Color(0xFF9930EF),
                                        Color(0xFF5737EE),
                                        Color(0xFF795CEB)
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(200f, 200f)
                                ),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable { onStartClick() }
                    ) {
                        Text( fontFamily = VariableFont,
                            text = "Start Chat",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text( fontFamily = VariableFont,
                    text = "Previous Chats",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(chats) { chat ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF222222)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onStartClick() }
                        ) {
                            Text( fontFamily = VariableFont,
                                text = chat,
                                fontSize = 18.sp,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onNotificationClick = {}, onStartClick = {})
}
