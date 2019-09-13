package com.example.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

            default:
                Toast.makeText(this,"Falló",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }

    }

}
