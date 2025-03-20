package seven.collector.soundverseai.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

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
