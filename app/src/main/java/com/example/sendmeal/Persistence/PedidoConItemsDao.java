package com.example.sendmeal.Persistence;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.PedidoConItems;
import com.example.sendmeal.Domain.Plato;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;

@Dao
public interface PedidoConItemsDao {
    @Query("SELECT * FROM pedido_items_join")
    List<PedidoConItems> getAll();

    @Insert
    void insertAll(PedidoConItems... joins);

    @Insert
    void insert(PedidoConItems join);

    @Delete
    void delete(PedidoConItems join);

    @Query("DELETE FROM pedido_items_join")
    void deleteAll();

    @Query("SELECT * FROM ItemPedido INNER JOIN pedido_items_join ON ItemPedido.id = pedido_items_join.item_id WHERE pedido_items_join.pedido_id =:pedidoId")
    List<ItemPedido> getItemsParaPedido(int pedidoId);




}
