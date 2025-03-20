package seven.collector.soundverseai.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit,
    onNotificationClick: (String) -> Unit,
) {
    val notifications = remember {
        listOf(
            NotificationItem(
                id = "1",
                title = "New Export Ready",
                message = "Your audio export is ready to share",
                timestamp = "2 mins ago",
                isRead = false,
                type = "export"
            ),
            NotificationItem(
                id = "2",
                title = "Processing Complete",
                message = "Your audio has been processed successfully",
                timestamp = "1 hour ago",
                isRead = true,
                type = "export"
            ),
            NotificationItem(
                id = "3",
                title = "New Comment",
                message = "Someone commented on your audio",
                timestamp = "3 hours ago",
                isRead = false,
                type = "comment"
            ),
            NotificationItem(
                id = "4",
                title = "Weekly Stats",
                message = "Check out your weekly listening stats",
                timestamp = "1 day ago",
                isRead = true,
                type = "stats"
            ),
            NotificationItem(
                id = "5",
                title = "New Feature Available",
                message = "Try our new audio filters in the latest update",
                timestamp = "2 days ago",
                isRead = true,
                type = "feature"
            )
        )
    }


}

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: String,
    val isRead: Boolean,
    val type: String,
)

@Composable
fun NotificationCard(
    notification: NotificationItem,
    onClick: () -> Unit,
) {

}