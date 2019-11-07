package com.example.sendmeal.Persistence;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    public static String _SERVER = "http://192.168.0.20:5000";
    private static final MyRetrofit ourInstance = new MyRetrofit();

    private Retrofit rf;

    public static MyRetrofit getInstance() {
        return ourInstance;
    }

    private MyRetrofit() {
        rf = new Retrofit.Builder()
                .baseUrl(_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PlatoRest crearPlatoRest(){
        return rf.create(PlatoRest.class);
    }

    public PedidoRest crearPedidoRest(){
        return rf.create(PedidoRest.class);
    }


}
