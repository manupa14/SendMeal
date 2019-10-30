package com.example.sendmeal.Domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Plato.class,
                parentColumns = "id",
                childColumns = "platoid",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Pedido.class,
                parentColumns = "id",
                childColumns = "idpedido",
                onDelete = ForeignKey.CASCADE)})

public class ItemPedido {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int platoid;
    private int cantidad;
    private int precio;
    private int idpedido;


    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPlatoid() {
        return platoid;
    }

    public void setPlatoid(int platoid) {
        this.platoid = platoid;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }



}
