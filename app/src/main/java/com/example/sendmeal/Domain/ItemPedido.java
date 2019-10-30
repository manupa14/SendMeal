package com.example.sendmeal.Domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    private Integer id;
    private Integer platoid;
    private Integer cantidad;
    private Double subTotal;
    private Integer idpedido;

    @Ignore
    private Plato Plato;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlatoid() {
        return platoid;
    }

    public void setPlatoid(Integer platoid) {
        this.platoid = platoid;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public com.example.sendmeal.Domain.Plato getPlato() {
        return Plato;
    }

    public void setPlato(com.example.sendmeal.Domain.Plato plato) {
        Plato = plato;
    }
}
