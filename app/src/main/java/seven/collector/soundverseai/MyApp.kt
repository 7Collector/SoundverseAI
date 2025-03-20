package seven.collector.soundverseai

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "export_channel",
            "Export Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notifies when an export is ready"
        }

        val manager = getSystemService(NotificationManager::class.java)
        manager?.createNotificationChannel(channel)
    }
}
