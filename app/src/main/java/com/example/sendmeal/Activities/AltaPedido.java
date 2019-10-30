package com.example.sendmeal.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AltaPedido extends AppCompatActivity {

    private Button btnCrear;
    private Button btnEnviar;
    private List<ItemPedido> itemsPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alta_pedido);

        inicializarComponentes();
        configurarEventos();

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


}
