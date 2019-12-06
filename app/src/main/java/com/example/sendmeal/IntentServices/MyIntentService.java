package com.example.sendmeal.IntentServices;

import android.app.IntentService;
import android.content.Intent;

import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;
import com.example.sendmeal.Receivers.MyReceiver;


public class MyIntentService extends IntentService {

    public MyIntentService () {
        super ("MyIntentService");
    }

    @Override
    protected void onHandleIntent (Intent intent){

        try{
            Plato platoSeleccionado = intent.getExtras().getParcelable("platoSeleccionado");
            Thread.currentThread().sleep(10000);
            Intent i = new Intent();
            i.putExtra("platoSeleccionado", platoSeleccionado);
            i.putExtra("Titulo", getString(R.string.tituloNotificacion));
            i.putExtra("Mensaje", getString(R.string.mensajeNotificacion));
            i.setAction(MyReceiver.EVENTO_OFERTAR);
            sendBroadcast(i);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }








}


