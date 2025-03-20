package seven.collector.soundverseai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import seven.collector.soundverseai.R
import seven.collector.soundverseai.utilities.shareVideoToInstagram

@Composable
fun ExportScreen(onDoneClick: () -> Unit, onBackClick: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF20006D), Color.Black),
                    start = Offset(0f, -368f),
                    end = Offset(0f, 367f)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.Black, CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = "Ready to share",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Saved to device and your library",
                fontSize = 13.sp,
                color = Color(0xFF939393),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(9f / 16f) // Vertical video aspect ratio
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_report_image), // Replace with your resource
                    contentDescription = "Content Preview",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1.0f))

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.CenterHorizontally)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF9164FF),
                                Color(0x009164FF)
                            ), start = Offset(0f, 500f), end = Offset(0f, 1000f)
                        )
                    )
            ) {
                Button(
                    onClick = { shareVideoToInstagram(context, "sample.mp4") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Transparent background
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFAE100),
                                            Color(0xFFF48224),
                                            Color(0xFFDE0A73),
                                            Color(0xFFB805AC),
                                            Color(0xFF7438EC)
                                        )
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.instagram),
                                contentDescription = "Instagram",
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Share to Instagram Stories",
                            fontWeight = FontWeight.W700,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.CenterHorizontally)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF9164FF),
                                Color(0x009164FF)
                            ), start = Offset(0f, 500f), end = Offset(0f, 1000f)
                        )
                    )
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.tiktok),
                                contentDescription = "Tiktok",
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Share to TikTok",
                            fontWeight = FontWeight.W700,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            // Bottom social icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialIcon(
                    name = "Instagram",
                    iconId = R.drawable.instagram,
                    backgroundColor = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFAE100),
                            Color(0xFFF48224),
                            Color(0xFFDE0A73),
                            Color(0xFFB805AC),
                            Color(0xFF7438EC)
                        )
                    )
                )

                SocialIcon(
                    name = "Whatsapp",
                    iconId = R.drawable.whatsapp,
                    backgroundColor = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF25D366), Color(0xFF25D366)
                        )
                    )
                )

                SocialIcon(
                    name = "Facebook",
                    iconId = R.drawable.facebook,
                    backgroundColor = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF1877F2), Color(0xFF1877F2)
                        )
                    )
                )

                SocialIcon(
                    name = "Youtube Shorts",
                    iconId = R.drawable.youtube_shorts,
                    backgroundColor = Brush.linearGradient(
                        colors = listOf(
                            Color.White, Color.White
                        )
                    )
                )

                SocialIcon(
                    name = "Other",
                    iconId = R.drawable.other_expoort,
                    backgroundColor = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF666666), Color(0xFF666666)
                        )
                    )
                )
            }

            Button(
                onClick = { onDoneClick() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF372955)
                )
            ) {
                Text(
                    text = "Done", color = Color.White, fontSize = 16.sp
                )
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun SocialIcon(name: String, iconId: Int, backgroundColor: Brush) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(backgroundColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = name,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = name, color = Color(0xFF939393), fontSize = 11.sp
        )
    }
}

@Preview
@Composable
fun ExportScreenPreview() {
    ExportScreen(onDoneClick = {}, onBackClick = {})
}