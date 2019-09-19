package com.example.sendmeal.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

public class AltaPlato extends AppCompatActivity {

    private EditText txtIdPlato;
    private EditText txtNombre;
    private EditText txtDescripcion;
    private EditText txtPrecio;
    private EditText txtCalorias;
    private Button btnRegistrarPlato;
    private Toolbar tbAltaPlato;
    //para probar
    public static List<Plato> listaPlatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altaplato);
        inicializarComponentes();
        setSupportActionBar(tbAltaPlato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();
    }

    private void inicializarComponentes(){
        txtIdPlato = findViewById(R.id.txtIdPlato);
        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCalorias = findViewById(R.id.txtCalorias);
        btnRegistrarPlato = findViewById(R.id.btnRegistrarPlato);
        tbAltaPlato = findViewById(R.id.tbAltaPlato);
        //esto es para probar
        listaPlatos=new ArrayList<Plato>();
    }

    private void configurarEventos(){

        btnRegistrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarVacio()){ // si faltan campos por completar
                    Toast.makeText(getApplicationContext(), R.string.faltanCampos, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.datosGuardados, Toast.LENGTH_SHORT).show();
                    //esto es para probar
                    Plato plato = new Plato(Integer.parseInt(txtIdPlato.getText().toString()),txtNombre.getText().toString(),txtDescripcion.getText().toString(),Double.parseDouble(txtPrecio.getText().toString()),Integer.parseInt(txtCalorias.getText().toString()));
                    listaPlatos.add(plato);
                }
            }
        });
    }

    private boolean validarVacio() {
        //Retorna true si ningun campo de la actividad es vacio
        return txtIdPlato.getText().length()>0 &&
                txtCalorias.getText().length()>0 &&
                txtDescripcion.getText().length()>0 &&
                txtNombre.getText().length()>0 &&
                txtPrecio.getText().length()>0;
    }


}


