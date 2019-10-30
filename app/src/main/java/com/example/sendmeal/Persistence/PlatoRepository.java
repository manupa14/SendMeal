package com.example.sendmeal.Persistence;

import android.content.Context;

public class PlatoRepository {

    private static PlatoRepository _INSTANCE = null;
    private PlatoDao platoDao;


    private PlatoRepository(Context ctx){
        platoDao = DBClient.getInstance(ctx).getAppDataBase().platoDao();
    }

    public static PlatoRepository getInstance(Context ctx){
        if(_INSTANCE == null) _INSTANCE = new PlatoRepository(ctx);
        return _INSTANCE;
    }

}