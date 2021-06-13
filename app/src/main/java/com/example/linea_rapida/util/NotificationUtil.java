package com.example.linea_rapida.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.linea_rapida.R;

public class NotificationUtil {

    private static final String CHANNEL_ID = "messages";
    private static final String CHANNEL_NAME = "Messages";
    private static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;

    private static int idCounter = 0;

    public static void createNotification(Context context, String title, String msg) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,CHANNEL_IMPORTANCE);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();
        notificationManager.notify(idCounter,notification);

        idCounter++;
    }
}
