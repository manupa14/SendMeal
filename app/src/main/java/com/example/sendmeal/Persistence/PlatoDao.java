package com.example.sendmeal.Persistence;
import com.example.sendmeal.Domain.Plato;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;

@Dao
public interface PlatoDao {
    @Query("SELECT * FROM plato")
    List<Plato> getAll();

    @Insert
    void insertAll(Plato... platoes);

    @Insert
    void insert(Plato plato);

    @Delete
    void delete(Plato plato);

    @Query("DELETE FROM plato")
    void deleteAll();
}
