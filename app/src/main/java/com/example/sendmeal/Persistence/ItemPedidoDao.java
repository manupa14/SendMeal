package com.example.sendmeal.Persistence;
import android.content.ClipData;

import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Plato;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;

@Dao
public interface ItemPedidoDao {
    @Query("SELECT * FROM itempedido")
    List<ItemPedido> getAll();

    @Insert
    void insertAll(ItemPedido... itemPedidos);

    @Insert
    void insert(ItemPedido itemPedido);

    @Delete
    void delete(ItemPedido itemPedido);

    @Query("DELETE FROM itemPedido")
    void deleteAll();
}
