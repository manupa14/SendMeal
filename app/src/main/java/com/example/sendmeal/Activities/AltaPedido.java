package com.example.sendmeal.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sendmeal.Adapters.ItemPedidoAdapter;
import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.PedidoRepository;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AltaPedido extends AppCompatActivity {

    RecyclerView myRecyclerView;
    ItemPedidoAdapter myItemPedidoAdapter;

    private Button btnCrear;
    private Button btnEnviar;
    private Button btnAgregarItem;
    private List<ItemPedido> itemsPedido = new ArrayList<>();

    Pedido pedido = new Pedido();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);

        inicializarComponentes();
        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if(startedFrom.equals("buscar")){
            Plato plato = getIntent().getParcelableExtra("plato");
            agregarItemPedido(plato);
        }

    }

    private void inicializarComponentes(){

        btnCrear = findViewById(R.id.btnCrear);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnAgregarItem = findViewById(R.id.fltBtnAgregarItem);

        btnEnviar.setEnabled(false);

    }

    private void configurarEventos(){

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido.setEstado(1);
                pedido.setFecha(new Date());
                pedido.setLat(-1);
                pedido.setLng(-1);
                pedido.setItems(itemsPedido);

                PedidoRepository.getInstance(getApplicationContext()).getPedidoDao().insert(pedido);

                btnCrear.setEnabled(false);
                btnAgregarItem.setVisibility(View.INVISIBLE);
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
                //Agregar mas items
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

        myRecyclerView = findViewById(R.id.rvAltaPedidos);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myItemPedidoAdapter);

    }


}
