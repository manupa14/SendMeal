package com.example.sendmeal.Domain;


import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;


public class ItemConPlato
{
    @Embedded
    public Plato plato;

    @Relation(parentColumn = "id", entityColumn = "platoid", entity = ItemPedido.class )
    public List<ItemPedido> itemPedidos;




}
