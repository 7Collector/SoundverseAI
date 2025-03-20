package seven.collector.soundverseai.utilities

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import seven.collector.soundverseai.screens.ExportScreen
import seven.collector.soundverseai.screens.HomeScreen
import seven.collector.soundverseai.screens.NotificationsScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object Notifications : Screen("notifications")
    data object ExportState : Screen("export_state")
}

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                /*onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },*/
                onNotificationClick = {
                    navController.navigate(Screen.Notifications.route)
                }
            )
        }

        /*composable(Screen.Profile.route) {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }*/

        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNotificationClick = { notificationType ->

                    navController.navigate(Screen.ExportState.route)
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
                /*
                onShareToInstagramClick = { videoUri ->

                }*/
            )
        }
    }
}