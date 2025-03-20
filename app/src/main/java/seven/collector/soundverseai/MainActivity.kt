package seven.collector.soundverseai

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import seven.collector.soundverseai.utilities.AppNavHost
import seven.collector.soundverseai.utilities.Constants.Companion.NOTIFICATION_TYPE
import seven.collector.soundverseai.utilities.Constants.Companion.NOTIFICATION_TYPE_EXPORT
import seven.collector.soundverseai.utilities.Screen

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { false /*viewModel.isLoading.value*/ }

        super.onCreate(savedInstanceState)

        setupNotifications()
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                HandleNotificationNavigation(navController, intent)
                AppNavHost(navController = navController)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

    }

    private fun setupNotifications() {
        // Request notification permissions for Android 13+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 100
            )
        }

        /* FirebaseMessaging.getInstance().subscribeToTopic("soundverse_notifications")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.logEvent("fcm_subscription_successful")
                } else {
                    viewModel.logEvent("fcm_subscription_failed")
                }
            }

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                viewModel.saveFcmToken(token)
            }
        } */
    }
}

@Composable
fun HandleNotificationNavigation(navController: NavHostController, intent: Intent?) {
    LaunchedEffect(intent) {
        val notificationType = intent?.getStringExtra(NOTIFICATION_TYPE)

        when (notificationType) {
            NOTIFICATION_TYPE_EXPORT -> {
                navController.navigate(Screen.ExportState.route)
            }
        }
    }
}