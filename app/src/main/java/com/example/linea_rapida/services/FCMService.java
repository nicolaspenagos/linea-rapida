package com.example.linea_rapida.services;

import androidx.annotation.NonNull;

import com.example.linea_rapida.model.FCMMessage;
import com.example.linea_rapida.util.NotificationUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        JSONObject object = new JSONObject(remoteMessage.getData());
        String json = object.toString();
        Gson gson = new Gson();
        FCMMessage fcmMessage = gson.fromJson(json, FCMMessage.class);

        NotificationUtil.createNotification(this, "Notificación",fcmMessage.getMessage());
    }
}
