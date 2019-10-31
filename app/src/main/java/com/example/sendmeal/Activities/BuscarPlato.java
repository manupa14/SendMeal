package com.example.sendmeal.Activities;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.SearchView;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BuscarPlato extends AppCompatActivity {

    List<Plato> listaDataSet = new ArrayList<>();
    private SearchView svBuscar;
    private RecyclerView myRecyclerView;
    private PlatoAdapter myPlatoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        inicializarComponentes();
        configurarEventos();

    }

    private void inicializarComponentes(){

        svBuscar = findViewById(R.id.svBuscar);
        svBuscar.setIconified(false);

        myRecyclerView = findViewById(R.id.rvBuscarPlatos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            listaDataSet = MyRetrofit.getInstance().getListaPlatos();
            switch (msg.arg1 ){
                case MyRetrofit._BUSCAR_PLATOS:
                    myPlatoAdapter = new PlatoAdapter(listaDataSet, getApplicationContext());
                    myRecyclerView.setAdapter(myPlatoAdapter);
                    break;
            }
        }
    };

    private void configurarEventos(){

        svBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MyRetrofit.getInstance().buscarPlatosPorNombre(getApplicationContext(), miHandler, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }


}
