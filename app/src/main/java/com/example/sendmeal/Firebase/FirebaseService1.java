package com.example.sendmeal.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FirebaseService1 extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,
                "Refreshed token: SERVICIO CREADO!!!!");
    }
    @Override
    public void onTokenRefresh() {
        String refreshedToken =
                FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        saveTokenToPrefs(refreshedToken);
    }
}


}
