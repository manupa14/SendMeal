package com.example.sendmeal.Persistence;

import android.content.Context;


public class PedidoRepository {

    private static PedidoRepository _INSTANCE = null;
    private PedidoDao pedidoDao;


    private PedidoRepository(Context ctx){
        pedidoDao = DBClient.getInstance(ctx).getAppDataBase().pedidoDao();
    }

    public static PedidoRepository getInstance(Context ctx){
        if(_INSTANCE == null) _INSTANCE = new PedidoRepository(ctx);
        return _INSTANCE;
    }

}