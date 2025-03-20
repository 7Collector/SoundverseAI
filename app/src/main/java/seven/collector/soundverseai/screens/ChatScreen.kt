package seven.collector.soundverseai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import seven.collector.soundverseai.R
import seven.collector.soundverseai.data.Message

@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            Message(
                text = "Hello there! I'm Soundverse Assistant, your music AI co-pilot. Let's get started with your project.",
                isFromUser = false
            ),
            Message(
                text = "You can write a prompt in the text box below. Try mentioning instruments, scene, story, genre, scale etc to generate an audio clip. More things you mention, the better the output will be.",
                isFromUser = false
            ),
            Message(
                text = "For e.g. you can write \"Compose a hauntingly beautiful piano solo that captures the essence of melancholy and nostalgia. The melody should evoke a sense of longing and introspection, while the harmonies add depth and emotion to the piece.\"",
                isFromUser = false,
                hasButton = true
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF9164FF), Color.Black),
                    start = Offset(0f, -LocalConfiguration.current.screenHeightDp * 0.4f),
                    end = Offset(0f, LocalConfiguration.current.screenHeightDp * 0.4f)
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /* TODO */ },
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

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.alpha(0.5f)
                ) {
                    Text(
                        text = "PULSE PLAYGROUND",
                        color = Color.White,
                        fontSize = 13.sp
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                items(messages) { message ->
                    ChatMessageBubble(message)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    color = Color(0xFF1A1625),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.import_image),
                            contentDescription = "Image",
                            modifier = Modifier.size(29.dp)
                        )

                        BasicTextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
                            modifier = Modifier.weight(1f).padding(0.dp),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.alpha(0.5f)) {
                                    if (messageText.isEmpty()) {
                                        Text(
                                            fontSize = 14.sp,
                                            text = "What would you like to create?",
                                            color = Color.White,
                                            modifier = Modifier.padding(0.dp),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )

                        Image(
                            painter = painterResource(R.drawable.tag),
                            contentDescription = "Settings",
                            modifier = Modifier.size(29.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.add_attach),
                            contentDescription = "Add",
                            modifier = Modifier.size(29.dp)
                        )

                        Box(
                            modifier = Modifier
                                .size(29.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2C2C2C)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(22.dp),
                                painter = painterResource(R.drawable.more_hor),
                                contentDescription = "More options",
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF512CAC)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.send),
                                contentDescription = "Send",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isFromUser) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_on_black),
                    contentDescription = "Soundverse Logo",
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
        }

        Column {
            Surface(
                shape = RoundedCornerShape(
                    topStart = if (message.isFromUser) 16.dp else 0.dp,
                    topEnd = if (message.isFromUser) 0.dp else 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                ),
                color = Color(0xFF181A1C),
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .defaultMinSize(minWidth = 50.dp)
                    .wrapContentWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = message.text,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                    if (message.hasButton) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
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
                                .clickable { /* TODO: Handle click */ }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Try Now",
                                color = Color.White,
                                fontSize = 13.sp
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
fun ChatScreenPreview() {
    ChatScreen()
}