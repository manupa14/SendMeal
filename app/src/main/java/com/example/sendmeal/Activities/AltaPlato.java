package com.example.sendmeal.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.PlatoRepository;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

public class AltaPlato extends AppCompatActivity {

    private EditText txtIdPlato;
    private EditText txtTitulo;
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
        setContentView(R.layout.activity_alta_plato);


        inicializarComponentes();
        setSupportActionBar(tbAltaPlato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if(startedFrom.equals("home")){
            listaPlatos = new ArrayList<Plato>();
        }
        if(startedFrom.equals("editar")){
            cargarDatosEditables();
        }
        if (startedFrom.equals("notificacion")){
            cargarDatosNoEditables();
        }


    }

    private void inicializarComponentes(){
        txtIdPlato = findViewById(R.id.txtIdPlato);
        txtTitulo = findViewById(R.id.txtTituloItem);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtPrecio = findViewById(R.id.txtPrecioItem);
        txtCalorias = findViewById(R.id.txtCalorias);
        btnRegistrarPlato = findViewById(R.id.btnRegistrarPlato);
        tbAltaPlato = findViewById(R.id.tbAltaPlato);
    }

    private void configurarEventos(){

        btnRegistrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarVacio()){ // si faltan campos por completar
                    Toast.makeText(getApplicationContext(), R.string.faltanCampos, Toast.LENGTH_SHORT).show();
                }
                else {
                    /*Preguntamos si accedimos desde home, para saber si debemos crear el plato o solo editarlo*/
                    if(getIntent().getExtras().getString("startedFrom").equals("home")) {
                        Plato plato = new Plato(Integer.parseInt(txtIdPlato.getText().toString()), txtTitulo.getText().toString(), txtDescripcion.getText().toString(), Double.parseDouble(txtPrecio.getText().toString()), Integer.parseInt(txtCalorias.getText().toString()));
                        listaPlatos.add(plato);
                        PlatoRepository.getInstance(getApplicationContext()).crearPlato(plato);
                    }
                    else
                    {
                        editarPlato();
                        setResult(RESULT_OK);
                        finish();
                    }
                    //Toast.makeText(getApplicationContext(), R.string.datosGuardados, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarVacio() {
        //Retorna true si ningun campo de la actividad es vacio
        return txtIdPlato.getText().length()>0 &&
                txtCalorias.getText().length()>0 &&
                txtDescripcion.getText().length()>0 &&
                txtTitulo.getText().length()>0 &&
                txtPrecio.getText().length()>0;
    }

    private void cargarDatosEditables(){
        //Este metodo carga en la actividad los datos del plato seleccionado para edicion.
        //Obtenemos el plato seleccionado y cargamos sus atributos en los edits texts

        Plato plato = obtenerPlatoSeleccionado();

        txtIdPlato.setText(plato.getIdPlato().toString(), TextView.BufferType.EDITABLE);
        txtTitulo.setText(plato.getTitulo(), TextView.BufferType.EDITABLE);
        txtDescripcion.setText(plato.getDescripcion(), TextView.BufferType.EDITABLE);
        txtPrecio.setText(plato.getPrecio().toString(), TextView.BufferType.EDITABLE);
        txtCalorias.setText(plato.getCalorias().toString(), TextView.BufferType.EDITABLE);
    }

    private Plato obtenerPlatoSeleccionado(){
        //Este metodo devuelve el objeto de tipo Plato seleccionado en la lista de platos.
        /*Obtenemos los extras del intent, especificamente la posicion del plato plato seleccionado en la lista.
        de este modo accedemos al elemento de la lista de platos*/
        int index;
        index = getIntent().getExtras().getInt("idPlatoSeleccionado");
        return listaPlatos.get(index);
    }

    private void editarPlato() {
        //Este metodo setea los nuevos valores de los atributos del plato seleccionado para edicion
        Plato plato = obtenerPlatoSeleccionado();
        plato.setIdPlato(Integer.parseInt(txtIdPlato.getText().toString()));
        plato.setTitulo(txtTitulo.getText().toString());
        plato.setDescripcion(txtDescripcion.getText().toString());
        plato.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
        plato.setCalorias(Integer.parseInt(txtCalorias.getText().toString()));
    }

    private void cargarDatosNoEditables() {
        ////Este metodo carga en la actividad los datos del plato pero no permite modificarlos
        Plato plato = obtenerPlatoSeleccionado();

        txtIdPlato.setText(plato.getIdPlato().toString(), TextView.BufferType.NORMAL);
        txtIdPlato.setEnabled(false);
        txtTitulo.setText(plato.getTitulo(), TextView.BufferType.NORMAL);
        txtTitulo.setEnabled(false);
        txtDescripcion.setText(plato.getDescripcion(), TextView.BufferType.NORMAL);
        txtDescripcion.setEnabled(false);
        txtPrecio.setText(plato.getPrecio().toString(), TextView.BufferType.NORMAL);
        txtPrecio.setEnabled(false);
        txtCalorias.setText(plato.getCalorias().toString(), TextView.BufferType.NORMAL);
        txtCalorias.setEnabled(false);
        btnRegistrarPlato.setVisibility(View.INVISIBLE);
    }




}


