package com.example.sendmeal.Persistence;

import android.content.Context;

public class ItemPedidoRepository {

    private static ItemPedidoRepository _INSTANCE = null;
    private ItemPedidoDao itemPedidoDao;


    private ItemPedidoRepository(Context ctx){
        itemPedidoDao = DBClient.getInstance(ctx).getAppDataBase().itemPedidoDao();
    }

    public static ItemPedidoRepository getInstance(Context ctx){
        if(_INSTANCE==null) _INSTANCE = new ItemPedidoRepository(ctx);
        return _INSTANCE;
    }

    public ItemPedidoDao getItemPedidoDao() {
        return itemPedidoDao;
    }
}