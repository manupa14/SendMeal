package com.example.sendmeal.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sendmeal.Adapters.PlatoAdapter;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Persistence.PlatoRepository;
import com.example.sendmeal.R;

import java.io.ByteArrayOutputStream;

public class AltaPlato extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 800;
    private EditText txtId;
    private EditText txtTitulo;
    private EditText txtDescripcion;
    private EditText txtPrecio;
    private EditText txtCalorias;
    private Button btnRegistrarPlato;
    private Toolbar tbAltaPlato;
    private Plato platoSeleccionado;
    private ImageButton btnCamara;
    private String encodedImage = "";
    private ImageView imgPlato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);

        inicializarComponentes();

        setSupportActionBar(tbAltaPlato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configurarEventos();

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        if (startedFrom.equals("editar")) {
            platoSeleccionado = getIntent().getExtras().getParcelable("platoSeleccionado");
            cargarDatosEditables(platoSeleccionado);
        }
        else {
            if (startedFrom.equals("notificacion")){
                platoSeleccionado = getIntent().getExtras().getParcelable("platoSeleccionado");
                cargarDatosNoEditables(platoSeleccionado);
            }
        }
    }

    public void setTxtTitulo(EditText txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public void setTxtDescripcion(EditText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public void setTxtPrecio(EditText txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public void setTxtCalorias(EditText txtCalorias) {
        this.txtCalorias = txtCalorias;
    }

    private void inicializarComponentes(){
        txtId = findViewById(R.id.txtIdPlato);
        txtId.setEnabled(false);

        txtTitulo = findViewById(R.id.txtTituloItem);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtPrecio = findViewById(R.id.txtPrecioItem);
        txtCalorias = findViewById(R.id.txtCalorias);
        btnRegistrarPlato = findViewById(R.id.btnRegistrarPlato);
        tbAltaPlato = findViewById(R.id.tbAltaPlato);
        btnCamara = findViewById(R.id.btnCamara);
        imgPlato = findViewById(R.id.imgPlato);
    }

    private void configurarEventos(){

        btnRegistrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarVacio()) { // si faltan campos por completar
                    Toast.makeText(getApplicationContext(), R.string.faltanCampos, Toast.LENGTH_SHORT).show();
                }
                else {
                    /*Preguntamos si accedimos desde home, para saber si debemos crear el plato o solo editarlo*/
                    if (getIntent().getExtras().getString("startedFrom").equals("home")) {
                        Plato plato = new Plato();

                        plato.setTitulo(txtTitulo.getText().toString());
                        plato.setDescripcion(txtDescripcion.getText().toString());
                        plato.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
                        plato.setCalorias(Integer.parseInt(txtCalorias.getText().toString()));
                        plato.setImagen(encodedImage);
                        plato.setEnOferta(false);

                        PlatoRepository.getInstance(getApplicationContext()).crearPlato(plato);

                        limpiarPantalla();
                    }
                    else {
                        editarPlato(platoSeleccionado);
                        setResult(RESULT_OK);
                        PlatoRepository.getInstance(getApplicationContext()).actualizarPlato(platoSeleccionado);
                        finish();
                    }

                }
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    public boolean validarVacio() {
        //Retorna true si ningun campo de la actividad es vacio
        return txtCalorias.getText().length()>0
                && txtDescripcion.getText().length()>0
                && txtTitulo.getText().length()>0
                && txtPrecio.getText().length()>0;
    }

    private void cargarDatosEditables(Plato plato){
        //Este metodo carga en la actividad los datos del plato seleccionado para edicion.
        txtId.setText(((Integer) plato.getId()).toString());
        txtTitulo.setText(plato.getTitulo(), TextView.BufferType.EDITABLE);
        txtDescripcion.setText(plato.getDescripcion(), TextView.BufferType.EDITABLE);
        txtPrecio.setText(plato.getPrecio().toString(), TextView.BufferType.EDITABLE);
        txtCalorias.setText(plato.getCalorias().toString(), TextView.BufferType.EDITABLE);

        encodedImage = plato.getImagen();
        if (!encodedImage.isEmpty()) {
            imgPlato.setImageBitmap(decodeImage(encodedImage));
        }
    }

    private void editarPlato(Plato plato) {
        //Este metodo setea los nuevos valores de los atributos del plato seleccionado para edicion
        plato.setTitulo(txtTitulo.getText().toString());
        plato.setDescripcion(txtDescripcion.getText().toString());
        plato.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
        plato.setCalorias(Integer.parseInt(txtCalorias.getText().toString()));
        plato.setImagen(encodedImage);
        ListaPlatos.listaDataSet.remove(plato);
        ListaPlatos.listaDataSet.add(plato);
    }

    private void cargarDatosNoEditables(Plato plato) {
        ////Este metodo carga en la actividad los datos del plato pero no permite modificarlos
        txtId.setText(((Integer) plato.getId()).toString());
        txtTitulo.setText(plato.getTitulo(), TextView.BufferType.NORMAL);
        txtTitulo.setEnabled(false);
        txtDescripcion.setText(plato.getDescripcion(), TextView.BufferType.NORMAL);
        txtDescripcion.setEnabled(false);
        txtPrecio.setText(plato.getPrecio().toString(), TextView.BufferType.NORMAL);
        txtPrecio.setEnabled(false);
        txtCalorias.setText(plato.getCalorias().toString(), TextView.BufferType.NORMAL);
        txtCalorias.setEnabled(false);
        btnCamara.setVisibility(View.INVISIBLE);

        encodedImage = plato.getImagen();
        if (!encodedImage.isEmpty()) {
            imgPlato.setImageBitmap(decodeImage(encodedImage));
        }
        imgPlato.setEnabled(false);

        btnRegistrarPlato.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            imgPlato.setImageBitmap(selectedImage);
            encodedImage = encodeImage(selectedImage);
        }
    }

    private String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] b = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(b,Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap decodeImage(String encodedImage) {
        byte[] decodedImage = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedImage,0,decodedImage.length);
    }

    private void limpiarPantalla() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCalorias.setText("");
    }

}



