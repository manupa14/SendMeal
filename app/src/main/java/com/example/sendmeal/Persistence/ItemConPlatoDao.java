package com.example.sendmeal.Persistence;


import com.example.sendmeal.Domain.ItemConPlato;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ItemConPlatoDao {

    @Query("SELECT * FROM Plato")
    public List<ItemConPlato> getPedidosPorPlato();


}
