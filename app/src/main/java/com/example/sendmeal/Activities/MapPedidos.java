package com.example.sendmeal.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.Persistence.PedidoRepository;
import com.example.sendmeal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class MapPedidos extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    public static final int CODIGO_MAPA = 123;
    public static final int BUSCAR_PEDIDOS_CREADOS = 1;
    public static final int BUSCAR_PEDIDOS_ENVIADOS = 2;

    private GoogleMap mMap;
    private Toolbar tbMapPedidos;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btnAgregarUbicacion;
    private LatLng latLng;
    private Handler mHandler;
    private Spinner spFiltro;
    private List<Pedido> pedidosCreados = new ArrayList<>();
    private List<Pedido> pedidosEnviados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tbMapPedidos = findViewById(R.id.tbMapPedidos);
        setSupportActionBar(tbMapPedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String startedFrom = getIntent().getExtras().getString("startedFrom");

        switch (startedFrom){
            case "home":
                configurarHandler();
                listarPedidosCreados();
                configurarFiltro();
                break;

            case "altaPedido":
                configurarBotonFlotante();
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        actualizarMapa();

        LatLng latLng = new LatLng(-31.61, -60.70);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        mMap.setOnMapLongClickListener(this);
    }

    private void actualizarMapa() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 9999:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    actualizarMapa();
                }
                break;
        }
    }

    @Override
    public void onMapLongClick(LatLng point){
        latLng = point;
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(latLng));
    }

    private void listarPedidosCreados(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                pedidosCreados = PedidoRepository.getInstance(getApplicationContext()).getPedidoDao().getAll();
                Message m = new Message();
                m.arg1 = BUSCAR_PEDIDOS_CREADOS;
                mHandler.sendMessage(m);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    public void configurarHandler(){
        mHandler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.arg1){
                    case BUSCAR_PEDIDOS_CREADOS:
                        mostrarPedidos(pedidosCreados, "CREADO", BitmapDescriptorFactory.HUE_YELLOW);
                        PedidoRepository.getInstance(getApplicationContext()).listarPedidosEnviados(mHandler);
                        break;

                    case BUSCAR_PEDIDOS_ENVIADOS:
                        pedidosEnviados = PedidoRepository.getInstance(getApplicationContext()).getPedidos();
                        mostrarPedidos(pedidosEnviados, "ENVIADO", BitmapDescriptorFactory.HUE_BLUE);
                        break;
                }
            }
        };
    }

    private void configurarFiltro(){

        spFiltro = findViewById(R.id.spEstados);
        String[] estados = {"TODOS", "CREADO", "ENVIADO"};
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estados);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFiltro.setAdapter(spAdapter);

        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Position: ", ((Integer)position).toString());

                mMap.clear();

                switch (position){
                    case 0:
                        mostrarPedidos(pedidosCreados, "CREADO", BitmapDescriptorFactory.HUE_YELLOW);
                        mostrarPedidos(pedidosEnviados, "ENVIADO", BitmapDescriptorFactory.HUE_BLUE);
                        break;
                    case 1:
                        mostrarPedidos(pedidosCreados, "CREADO", BitmapDescriptorFactory.HUE_YELLOW);
                        break;
                    case 2:
                        mostrarPedidos(pedidosEnviados, "ENVIADO", BitmapDescriptorFactory.HUE_BLUE);
                        unirPedidos();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configurarBotonFlotante(){

        btnAgregarUbicacion = findViewById(R.id.fltBtnAgregarUbicacion);

        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), AltaPedido.class);

                i.putExtra("latitud", latLng.latitude);
                i.putExtra("longitud", latLng.longitude);

                setResult(RESULT_OK, i);

                finish();
            }
        });

    }

    private void mostrarPedidos(List<Pedido> pedidosAMostrar, String estado, float color) {
        if(!pedidosAMostrar.isEmpty()){
            for (Pedido pedido : pedidosAMostrar) {
                LatLng latLng = new LatLng(pedido.getLatitud(), pedido.getLongitud());
                mMap.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(color))
                        .title("id: " + ((Long) pedido.getIdPedido()).toString())
                        .snippet("Estado: " + estado));
            }
        }
    }

    public void unirPedidos(){
        PolylineOptions rectOptions = new PolylineOptions();
        for (Pedido p: pedidosEnviados){
            rectOptions.add(new LatLng(p.getLatitud(), p.getLongitud()));
        }
        Polyline polyline = mMap.addPolyline(rectOptions);
    }
}
