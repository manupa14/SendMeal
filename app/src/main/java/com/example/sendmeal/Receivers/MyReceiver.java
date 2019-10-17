package com.example.sendmeal.Receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sendmeal.Activities.AltaPlato;
import com.example.sendmeal.Activities.ListaPlatos;
import com.example.sendmeal.R;



public class MyReceiver extends BroadcastReceiver {

    public static final String EVENTO_OFERTAR = "com.example.sendmeal.notificacionOferta";
    private static final int ID_NOTIFICACION_OFERTAR = 99;


    @Override
    public void onReceive (Context context, Intent intent) {

        int position = intent.getExtras().getInt("idPlatoSeleccionado");

        //Definimos que hacer cuando seleccionamos la notificacion
        Intent destino = new Intent(context, AltaPlato.class);

       /*Enviamos el id del plato seleccionado y un string para avisarle a la actividad AltaPlato
       * que la inicamos desde la notificacion*/
        destino.putExtra("idPlatoSeleccionado",position);
        destino.putExtra("startedFrom","notificacion");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context,0,destino,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(context, ListaPlatos.CANAL_MENSAJES_ID);
        myBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        myBuilder.setContentTitle(intent.getExtras().getString("Titulo"));
        myBuilder.setContentText(intent.getExtras().getString("Mensaje"));
        myBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //Para que abra la actividad alta plato
        myBuilder.setContentIntent(pendingIntent);
        //Para que la notificacion salga de la barra de tareas
        myBuilder.setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(ID_NOTIFICACION_OFERTAR,myBuilder.build());

    }




}
