package com.example.sendmeal.Activities;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.SearchView;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.PlatoRepository;
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

    Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            listaDataSet = PlatoRepository.getInstance(getApplicationContext()).getListaPlatos();
            switch (msg.arg1 ){
                case PlatoRepository._BUSCAR_PLATOS:
                    myPlatoAdapter = new PlatoAdapter(listaDataSet, getApplicationContext(),1);
                    myRecyclerView.setAdapter(myPlatoAdapter);
                    break;
            }
        }
    };

    private void configurarEventos(){

        svBuscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PlatoRepository.getInstance(getApplicationContext()).buscarPlatosPorNombre(myHandler, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }


}
