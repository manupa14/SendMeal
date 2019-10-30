package com.example.sendmeal.Persistence;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.room.*;
import com.example.sendmeal.Domain.*;

@Database(entities = {Plato.class, Pedido.class, ItemPedido.class, }, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();
    public abstract ItemPedidoDao itemPedidoDao();


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}