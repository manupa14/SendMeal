package com.example.sendmeal.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sendmeal.R;
import com.example.sendmeal.Receivers.MyReceiver;

public class BroadcastNotification extends AppCompatActivity {

    public static final String CANAL_MENSAJES_ID="10001";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            BroadcastReceiver myBroadcastReceiver = new MyReceiver();
            IntentFilter filtro = new IntentFilter();
            filtro.addAction(MyReceiver.EVENTO_OFERTAR);
            getApplication().getApplicationContext().registerReceiver(myBroadcastReceiver,filtro);
        }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.nombreCanal);
            String description = getString(R.string.descripcionCanal);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CANAL_MENSAJES_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    }
