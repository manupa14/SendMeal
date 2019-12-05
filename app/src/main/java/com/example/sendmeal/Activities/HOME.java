package com.example.sendmeal.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sendmeal.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class HOME extends AppCompatActivity {


    private androidx.appcompat.widget.Toolbar tbHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tbHome = findViewById(R.id.tbHome);

        setSupportActionBar(tbHome);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.itemRegistrar:
                Intent i1=new Intent(this, Registro.class);
                startActivity(i1);
                return true;

            case R.id.itemCrear:
                Intent i2=new Intent( this, AltaPlato.class);
                i2.putExtra("startedFrom","home");
                startActivity(i2);
                return true;

            case R.id.itemLista:
                Intent i3=new Intent(this, ListaPlatos.class);
                startActivity(i3);
                return true;

            case R.id.itemPedido:
                Intent i4=new Intent(this, BuscarPlato.class);
                startActivity(i4);
                return true;

            case R.id.verPedidos:
                Intent i5=new Intent(this, MapPedidos.class);
                i5.putExtra("startedFrom", "home");
                startActivity(i5);
                return true;

            default:
                Toast.makeText(this,"Falló",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }

    }

}
