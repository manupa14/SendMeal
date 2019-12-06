package com.example.sendmeal.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.PlatoRepository;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BuscarPlato extends AppCompatActivity {

    public static List<Plato> listaDataSet = new ArrayList<>();
    private Toolbar tbBuscar;
    private SearchView svBuscar;
    private Button btnVerPedido;
    private RecyclerView myRecyclerView;
    private PlatoAdapter myPlatoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        inicializarComponentes();
        configurarEventos();
        setSupportActionBar(tbBuscar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void inicializarComponentes(){

        tbBuscar = findViewById(R.id.tbBuscar);
        svBuscar = findViewById(R.id.svBuscar);
        svBuscar.setIconified(false);
        btnVerPedido = findViewById(R.id.btnVerPedido);

        myRecyclerView = findViewById(R.id.rvBuscarPlatos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            listaDataSet = PlatoRepository.getInstance(getApplicationContext()).getListaPlatos();
            switch (msg.arg1){
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

        btnVerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AltaPedido.class);
                i.putExtra("startedFrom","verPedido");
                startActivity(i);
            }
        });


    }


}
