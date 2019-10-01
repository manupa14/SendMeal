package com.example.sendmeal.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListaPlatos extends AppCompatActivity {

    RecyclerView myRecyclerView;
    PlatoAdapter myPlatoAdapter;
    List<Plato> myListaPlatos;
    Toolbar tbListaPlatos;
    private static final int CODIGO_OFERTAR_PLATO = 1;
    private static final int CODIGO_EDITAR_PLATO = 2;
    private static final int CODIGO_QUITAR_PLATO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);
        inicializarComponentes();
        setSupportActionBar(tbListaPlatos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();

        myListaPlatos = AltaPlato.listaPlatos;

        myPlatoAdapter = new PlatoAdapter(myListaPlatos, this);

        myRecyclerView = findViewById(R.id.rvListaPlatos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myPlatoAdapter);

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
                case CODIGO_OFERTAR_PLATO:
                    break;

                case CODIGO_EDITAR_PLATO:
                    //le avisamos a nuestro adaptador que cambiaron los datos
                    myPlatoAdapter.notifyDataSetChanged();
                    break;

                case CODIGO_QUITAR_PLATO:
                    break;
            }

        }
        else{
            switch(requestCode) {
                case CODIGO_OFERTAR_PLATO:
                    Toast.makeText(getApplicationContext(), R.string.falloOfertar, Toast.LENGTH_SHORT).show();
                    break;
                case CODIGO_EDITAR_PLATO:
                    Toast.makeText(getApplicationContext(), R.string.falloEditar, Toast.LENGTH_SHORT).show();
                    break;
                case CODIGO_QUITAR_PLATO:
                    Toast.makeText(getApplicationContext(), R.string.falloOfertar, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }


}
