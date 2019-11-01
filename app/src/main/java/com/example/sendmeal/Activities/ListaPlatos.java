package com.example.sendmeal.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;
import com.example.sendmeal.Receivers.MyReceiver;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListaPlatos extends AppCompatActivity {

    RecyclerView myRecyclerView;
    PlatoAdapter myPlatoAdapter;
    List<Plato> myListaPlatos;
    Toolbar tbListaPlatos;
    private static final int CODIGO_EDITAR_PLATO = 1;



    //---------
    public static final String CANAL_MENSAJES_ID="10001";
    //-----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);
        inicializarComponentes();
        setSupportActionBar(tbListaPlatos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();

        myListaPlatos = AltaPlato.listaPlatos;

        myPlatoAdapter = new PlatoAdapter(myListaPlatos, this, 0);

        myRecyclerView = findViewById(R.id.rvListaPlatos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myPlatoAdapter);

        //Creamos canal y registramos el BroadcastReceiver
        createNotificationChannel();
        BroadcastReceiver br = new MyReceiver();
        IntentFilter filtro = new IntentFilter();
        filtro.addAction(MyReceiver.EVENTO_OFERTAR);
        getApplication().getApplicationContext().registerReceiver(br,filtro);
        //---------------



    }


    private void inicializarComponentes() {
        myListaPlatos = new ArrayList<Plato>();
        tbListaPlatos = findViewById(R.id.tbListaPlatos);
    }

    private void configurarEventos() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        if(resultCode == RESULT_OK){
            switch (requestCode){

                case CODIGO_EDITAR_PLATO:
                    //le avisamos a nuestro adaptador que cambiaron los datos
                    myPlatoAdapter.notifyDataSetChanged();
                    break;

            }

        }
        else{
            switch(requestCode) {
                case CODIGO_EDITAR_PLATO:
                    Toast.makeText(getApplicationContext(), R.string.falloEditar, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    //------------------------------------------------------------
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
