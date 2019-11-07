package com.example.sendmeal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sendmeal.Adapters.ItemPedidoAdapter;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.ItemPedidoRepository;
import com.example.sendmeal.Persistence.PedidoRepository;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AltaPedido extends AppCompatActivity {

    RecyclerView myRecyclerView;
    ItemPedidoAdapter myItemPedidoAdapter;

    private Toolbar tbAltaPedido;
    private Button btnCrear;
    private Button btnEnviar;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btnAgregarItem;
    Pedido pedido = new Pedido();


    //TODO ver por que se cargar platos iguales al pedido
    //TODO agreagar precio total
    //TODO Lista Platos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);

        inicializarComponentes();
        setSupportActionBar(tbAltaPedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if(startedFrom.equals("buscar")){
            Plato plato = getIntent().getParcelableExtra("plato");
            agregarItemPedido(plato);
        }
        if(startedFrom.equals("verPedido")) {
            myItemPedidoAdapter = new ItemPedidoAdapter(PedidoRepository.getInstance(getApplicationContext()).getItemsPedido(), this);
            myRecyclerView.setAdapter(myItemPedidoAdapter);
        }

    }

    private void inicializarComponentes(){

        tbAltaPedido = findViewById(R.id.tbAltaPedido);
        btnCrear = findViewById(R.id.btnCrear);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnAgregarItem = findViewById(R.id.fltBtnAgregarItem);

        myRecyclerView = findViewById(R.id.rvAltaPedidos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        btnEnviar.setEnabled(false);

    }

    private void configurarEventos(){

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido.setEstado(1);
                pedido.setFecha(new Date());
                pedido.setLatitud(-1.0);
                pedido.setLongitud(-1.0);

                Long id = PedidoRepository.getInstance(getApplicationContext()).getPedidoDao().insert(pedido);

                for(ItemPedido itemPedido: PedidoRepository.getInstance(getApplicationContext()).getItemsPedido()) {

                    itemPedido.setIdPedido(id);
                    ItemPedidoRepository.getInstance(getApplicationContext()).getItemPedidoDao().insert(itemPedido);

                }

                PedidoRepository.getInstance(getApplicationContext()).setItemsPedido(new ArrayList<ItemPedido>());

                btnCrear.setEnabled(false);
                btnAgregarItem.setEnabled(false);
                btnEnviar.setEnabled(true);

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido.setEstado(2);
                PedidoRepository.getInstance(getApplicationContext()).guardarPedidoEnviado(pedido);

            }
        });

        btnAgregarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),BuscarPlato.class);
                startActivity(i);
            }
        });



    }

    private void agregarItemPedido(Plato plato){

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setPlato(plato);
        itemPedido.setSubTotal(plato.getPrecio());
        itemPedido.setCantidad(1);

        PedidoRepository.getInstance(getApplicationContext()).getItemsPedido().add(itemPedido);

        myItemPedidoAdapter = new ItemPedidoAdapter(PedidoRepository.getInstance(getApplicationContext()).getItemsPedido(), this);

        myRecyclerView.setAdapter(myItemPedidoAdapter);

    }


}
