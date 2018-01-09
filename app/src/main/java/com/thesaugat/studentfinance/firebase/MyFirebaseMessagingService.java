package com.thesaugat.studentfinance.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.thesaugat.studentfinance.HomeActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        String message = remoteMessage.getNotification().getBody();
        intent.putExtra("message", message);
        startActivity(intent);
        System.out.println("notification message " + message);
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }


}