package com.example.sendmeal.Persistence;
import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.Domain.Plato;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;

@Dao
public interface PedidoDao {

    @Query("SELECT * FROM Pedido")
    List<Pedido> getAll();

    @Insert
    void insertAll(Pedido... pedidos);

    @Insert
    long insert(Pedido pedido);

    @Delete
    void delete(Pedido pedido);

    @Query("DELETE FROM pedido")
    void deleteAll();

    @Query("DELETE FROM pedido where pedido.pedido_id = :id")
    void deleteByID(long id);

    @Query("SELECT * FROM pedido where pedido.pedido_id = :pid ")
    Pedido findById(long pid);
}
