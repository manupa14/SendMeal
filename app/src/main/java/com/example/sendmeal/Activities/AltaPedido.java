package com.example.sendmeal.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sendmeal.Adapters.ItemPedidoAdapter;
import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AltaPedido extends AppCompatActivity {

    private Button btnCrear;
    private Button btnEnviar;
    private List<ItemPedido> itemsPedido;

    RecyclerView myRecyclerView;
    ItemPedidoAdapter myItemPedidoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alta_pedido);

        inicializarComponentes();
        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if(startedFrom.equals("buscar")){
            //recuperar plato del intent.
            //agregarItemPedido(plato)
        }

    }

    private void inicializarComponentes(){

        //btnCrear = findViewById(R.id.btnCrear);
        //btnEnviar = findViewById(R.id.btnEnviar);
        itemsPedido = new ArrayList<>();

    }

    private void configurarEventos(){

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    private void agregarItemPedido(Plato plato){

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setPlato(plato);
        itemPedido.setSubTotal(plato.getPrecio());
        itemPedido.setCantidad(1);

        itemsPedido.add(itemPedido);

        myItemPedidoAdapter = new ItemPedidoAdapter(itemsPedido, this);

        //myRecyclerView = findViewById(R.id.rvItemsPedido);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myItemPedidoAdapter);

    }


}
