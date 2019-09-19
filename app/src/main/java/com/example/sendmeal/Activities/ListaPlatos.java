package com.example.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.R;

import java.util.ArrayList;

public class ListaPlatos extends AppCompatActivity {

    RecyclerView myRecyclerView;
    PlatoAdapter myPlatoAdapter;
    List<Plato> myListaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        myListaPlatos = new ArrayList<Plato>();

        //OBTENER PLATOS Y CARGAR LISTAS

        /*PARA PROBAR VISUALIZACION
        for(int i=0;i<TvShows.length;i++)
        {
            TvShow tvShow = new TvShow();

            tvShow.setTvshow(TvShows[i]);
            tvShow.setImgTvshow(TvShowImgs[i]);

            tvShows.add(tvShow);
        }*/

        myPlatoAdapter = new PlatoAdapter(myListaPlatos);

        myRecyclerView = findViewById(R.id.rvListaPlatos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myPlatoAdapter);

    }


}
