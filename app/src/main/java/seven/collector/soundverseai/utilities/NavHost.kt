package seven.collector.soundverseai.utilities

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import seven.collector.soundverseai.screens.ChatScreen
import seven.collector.soundverseai.screens.ExportScreen
import seven.collector.soundverseai.screens.HomeScreen
import seven.collector.soundverseai.screens.NotificationsScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Chat : Screen("chat")
    data object Notifications : Screen("notifications")
    data object ExportState : Screen("export_state")
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                /*onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },*/
                onStartClick = {
                    navController.navigate(Screen.Chat.route)
                },
                onNotificationClick = {
                    navController.navigate(Screen.Notifications.route)
                }
            )
        }

        composable(Screen.Chat.route) {
            ChatScreen(
                onClick = {
                    navController.navigate(Screen.ExportState.route)
                }
            )
        }

        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNotificationClick = { notificationType ->
                    if (notificationType == "export") {
                    navController.navigate(Screen.ExportState.route)
                    } else {
                        Toast.makeText(context, "Clicked on $notificationType", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        composable(Screen.ExportState.route) {
            ExportScreen(
                onDoneClick = {
                    navController.navigate(Screen.Notifications.route) {

                        popUpTo(Screen.Notifications.route) {
                            inclusive = true
                        }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}