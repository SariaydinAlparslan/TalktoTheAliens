

package com.sariaydinalparslan.talkthealiens.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sariaydinalparslan.talkthealiens.Entity.Messages
import com.sariaydinalparslan.talkthealiens.R


class MyFireBaseMessagingService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }
    override fun onNewToken(token: String) {
        Log.i(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body


        val user = Firebase.auth.currentUser

        //ortak channel
        /*val db = FirebaseDatabase.getInstance()
        val refMessages=db.getReference("messages")
        val yenimesaj = Messages("$title","")
        refMessages.push().setValue(yenimesaj)*/

        //direct
        val db = FirebaseDatabase.getInstance()
        val privateRefMessages=db.getReference("message${user?.uid.toString()}")
        val yeniprivatemesaj = Messages("$title","","","")
        privateRefMessages.push().setValue(yeniprivatemesaj)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationBuilder = if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationCompat.Builder(applicationContext, "1")
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        /*val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_IMMUTABLE)
*/

            notificationBuilder = notificationBuilder
            .setSmallIcon(R.drawable.alien)
                //.addAction(R.drawable.alien,"Open Message",pendingIntent)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        initNotificationChannel(notificationManager)
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())

    }
    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            notificationManager.createNotificationChannelIfNOtExists(
                channelId = "1",
                channelName = "Default"
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNOtExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}