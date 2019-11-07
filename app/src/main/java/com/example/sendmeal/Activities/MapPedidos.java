package com.example.sendmeal.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.sendmeal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


public class MapPedidos extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    public static final int CODIGO_MAPA = 123;
    private GoogleMap mMap;
    private Toolbar tbMapPedidos;
    private com.google.android.material.floatingactionbutton.FloatingActionButton btnAgregarUbicacion;
    private LatLng latLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        inicializarComponentes();
        configurarEventos();

        setSupportActionBar(tbMapPedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicializarComponentes(){

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tbMapPedidos = findViewById(R.id.tbMapPedidos);

        btnAgregarUbicacion = findViewById(R.id.fltBtnAgregarUbicacion);

    }

    private void configurarEventos(){

        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AltaPedido.class);
                i.putExtra("latitud", latLng.latitude);
                i.putExtra("longitud", latLng.longitude);
                setResult(RESULT_OK, i);
                finish();
            }
        });

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
                //.title(R.string.miUbicacion));

    }

}
