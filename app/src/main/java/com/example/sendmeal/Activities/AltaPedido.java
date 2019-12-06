package com.example.sendmeal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sendmeal.Adapters.ItemPedidoAdapter;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.ItemPedidoRepository;
import com.example.sendmeal.Persistence.PedidoRepository;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AltaPedido extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemPedidoAdapter mItemPedidoAdapter;
    private Toolbar tbAltaPedido;
    private Button btnCrear;
    private Button btnEnviar;
    private ImageButton btnSetMapa;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btnAgregarItem;
    private Pedido pedido = new Pedido();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedido);

        inicializarComponentes();

        setSupportActionBar(tbAltaPedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if (startedFrom.equals("buscar")) {
            Plato plato = getIntent().getParcelableExtra("plato");
            agregarItemPedido(plato);
        }
        else {
            if (startedFrom.equals("verPedido")) {
                mItemPedidoAdapter = new ItemPedidoAdapter(
                        PedidoRepository.getInstance(this).getItemsPedido(), this);
                mRecyclerView.setAdapter(mItemPedidoAdapter);
            }
        }
    }

    private void inicializarComponentes(){
        tbAltaPedido = findViewById(R.id.tbAltaPedido);

        btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setEnabled(false);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setEnabled(false);

        btnAgregarItem = findViewById(R.id.fltBtnAgregarItem);

        btnSetMapa = findViewById(R.id.btnSetMapa);

        mRecyclerView = findViewById(R.id.rvAltaPedidos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void configurarEventos(){
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.setEstado(1);
                pedido.setFecha(new Date());
                pedido.setItems(PedidoRepository.getInstance(getApplicationContext()).getItemsPedido());

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        long idCreado = PedidoRepository.getInstance(getApplicationContext()).getPedidoDao().insert(pedido);

                        pedido.setIdPedido(idCreado);//Guardo el id en el pedido para luego borrarlo.

                        for (ItemPedido itemPedido: PedidoRepository.getInstance(getApplicationContext()).getItemsPedido()) {
                            itemPedido.setIdPedido(idCreado);
                            ItemPedidoRepository.getInstance(getApplicationContext()).getItemPedidoDao().insert(itemPedido);
                        }

                        PedidoRepository.getInstance(getApplicationContext()).setItemsPedido(new ArrayList<ItemPedido>());
                    }
                };
                Thread thread = new Thread(r);
                thread.start();

                btnCrear.setEnabled(false);
                btnAgregarItem.setEnabled(false);
                btnEnviar.setEnabled(true);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        PedidoRepository.getInstance(getApplicationContext()).getPedidoDao().deleteByID(pedido.getIdPedido());
                    }
                };
                Thread thread = new Thread(r);
                thread.start();

                pedido.setEstado(2);

                PedidoRepository.getInstance(getApplicationContext()).guardarPedidoEnviado(pedido);
            }
        });

        btnAgregarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BuscarPlato.class);
                startActivity(i);
            }
        });

        btnSetMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapPedidos.class);
                i.putExtra("startedFrom", "altaPedido");
                startActivityForResult(i, MapPedidos.CODIGO_MAPA);
            }
        });
    }

    private void agregarItemPedido(Plato plato){
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setPlato(plato);
        itemPedido.setSubTotal(plato.getPrecio());
        itemPedido.setCantidad(1);

        PedidoRepository.getInstance(getApplicationContext()).getItemsPedido().add(itemPedido);

        mItemPedidoAdapter = new ItemPedidoAdapter(PedidoRepository.getInstance(getApplicationContext()).getItemsPedido(), this);

        mRecyclerView.setAdapter(mItemPedidoAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MapPedidos.CODIGO_MAPA:
                    pedido.setLatitud(data.getExtras().getDouble("latitud"));
                    pedido.setLongitud(data.getExtras().getDouble("longitud"));
                    btnCrear.setEnabled(true);
                    break;
            }
        }
        else {
            switch (requestCode){
                case MapPedidos.CODIGO_MAPA:
                    Toast.makeText(getApplicationContext(), R.string.falloMapa, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
