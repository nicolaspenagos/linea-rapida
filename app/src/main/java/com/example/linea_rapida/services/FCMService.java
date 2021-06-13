package com.example.linea_rapida.services;

import androidx.annotation.NonNull;

import com.example.linea_rapida.util.NotificationUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
