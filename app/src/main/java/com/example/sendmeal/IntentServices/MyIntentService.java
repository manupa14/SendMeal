package com.example.sendmeal.IntentServices;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.example.sendmeal.Activities.ListaPlatos;
import com.example.sendmeal.R;
import com.example.sendmeal.Receivers.MyReceiver;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyIntentService extends IntentService {

    public MyIntentService () {
        super ("MyIntentService");
    }

    @Override
    protected void onHandleIntent (Intent intent){

        try{
            int position = intent.getExtras().getInt("idPlatoSeleccionado");
            Thread.currentThread().sleep(10000);
            Intent i = new Intent();
            i.putExtra("idPlatoSeleccionado",position);
            i.putExtra("Titulo", getString(R.string.tituloNotificacion));
            i.putExtra("Mensaje", getString(R.string.mensajeNotificacion));
            i.setAction(MyReceiver.EVENTO_OFERTAR);
            sendBroadcast(i);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }








}


