package com.example.sendmeal.Receivers;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sendmeal.Activities.BroadcastNotification;
import com.example.sendmeal.Activities.ListaPlatos;
import com.example.sendmeal.R;



public class MyReceiver extends BroadcastReceiver {

    public static final String EVENTO_OFERTAR = "com.example.sendmeal.notificacionOferta";


    @Override
    public void onReceive (Context context, Intent intent) {
        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(context, ListaPlatos.CANAL_MENSAJES_ID);
        myBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        myBuilder.setContentTitle(intent.getExtras().getString("Titulo"));
        myBuilder.setContentText(intent.getExtras().getString("Mensaje"));
        myBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(10,myBuilder.build());
    }




}
