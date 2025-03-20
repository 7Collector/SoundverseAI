package seven.collector.soundverseai.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import seven.collector.soundverseai.MainActivity
import seven.collector.soundverseai.R
import seven.collector.soundverseai.utilities.Constants.Companion.NOTIFICATION_TYPE
import seven.collector.soundverseai.utilities.Constants.Companion.NOTIFICATION_TYPE_EXPORT
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

val VariableFont = FontFamily(
    Font(R.font.urbanist_v, weight = FontWeight.Normal) // Variable font
)

fun shareVideoToInstagram(context: Context, assetFileName: String) {
    val instagramPackage = "com.instagram.android"
    val tag = "ShareVideo"

    Log.d(tag, "Attempting to share video: $assetFileName")

    if (context.packageManager.getLaunchIntentForPackage(instagramPackage) == null) {
        Log.e(tag, "Instagram is not installed on this device.")
        return
    }

    val videoFile = File(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES), assetFileName)
    if (!videoFile.exists()) {
        try {
            context.assets.open(assetFileName).use { inputStream ->
                FileOutputStream(videoFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            Log.d(tag, "Video file copied to: ${videoFile.absolutePath}")
        } catch (e: Exception) {
            Log.e(tag, "Error copying video file: ${e.message}", e)
            return
        }
    } else {
        Log.d(tag, "Video file already exists: ${videoFile.absolutePath}")
    }

    val fileUri: Uri = try {
        FileProvider.getUriForFile(context, "${context.packageName}.provider", videoFile)
    } catch (e: Exception) {
        Log.e(tag, "Error getting FileProvider URI: ${e.message}", e)
        return
    }

    Log.d(tag, "File URI obtained: $fileUri")

    val intent = Intent("com.instagram.share.ADD_TO_STORY").apply {
        setDataAndType(fileUri, "video/mp4")
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        Log.d(tag, "Starting Instagram story share intent.")
        context.startActivity(intent)
    } else {
        Log.e(tag, "Instagram does not support the story share intent.")
    }
}

fun showNotification(applicationContext: Context) {
    val intent = Intent(applicationContext, MainActivity::class.java).apply {
        putExtra(NOTIFICATION_TYPE, NOTIFICATION_TYPE_EXPORT)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(applicationContext, "export_channel")
        .setSmallIcon(R.drawable.logo_on_black)
        .setContentTitle("New Export Ready")
        .setContentText("Your audio export is ready to share")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build()

    notificationManager.notify(1, notification)
}