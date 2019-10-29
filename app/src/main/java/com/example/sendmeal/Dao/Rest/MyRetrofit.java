package com.example.sendmeal.Dao.Rest;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.sendmeal.Activities.AltaPlato;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MyRetrofit {

    public static String _SERVER = "http://10.0.2.2:5000";
    private static final MyRetrofit ourInstance = new MyRetrofit();

    private PlatoRest platoRest;

    private List<Plato> listaPlatos = new ArrayList<Plato>();

    public static final int _BUSCAR_PLATOS = 1;

    public static MyRetrofit getInstance() {
        return ourInstance;
    }

    private MyRetrofit() {

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.d("APP_2","INSTANCIA CREADA");
        platoRest = rf.create(PlatoRest.class);
    }

    public PlatoRest getPlatoRest() {
        return platoRest;
    }

    public void setPlatoRest(PlatoRest platoRest) {
        this.platoRest = platoRest;
    }



    public void crearPlato(final Context context, Plato p){

        Call<Plato> llamada = this.platoRest.crear(p);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, R.string.datosGuardados, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Toast.makeText(context, R.string.falloCrear, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buscarPlatosPorNombre(final Context context, final Handler h, String nombre){
        Call<List<Plato>> llamada = this.platoRest.buscarPorNombre(nombre);
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _BUSCAR_PLATOS;
                    h.sendMessage(m);
                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(context, R.string.falloCrear, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Plato> getListaPlatos(){
        return listaPlatos;
    }

    /*public List<Plato> buscarTodos(final Context context, final Handler h){
        Call<List<Plato>> llamada = this.platoRest.buscarTodos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());


                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(context, R.string.falloCrear, Toast.LENGTH_SHORT).show();
            }
        });

        return listaPlatos;
    }*/


}




