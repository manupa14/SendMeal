package com.example.sendmeal.Domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(foreignKeys = {
        @ForeignKey(entity = Plato.class,
                parentColumns = "plato_id",
                childColumns = "plato_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Pedido.class,
                parentColumns = "pedido_id",
                childColumns = "pedido_id",
                onDelete = ForeignKey.CASCADE)})

public class ItemPedido {

    @PrimaryKey(autoGenerate = true)
    private Integer idItemPedido;

    @ColumnInfo(name = "pedido_id")
    private Integer idPedido;

    @ColumnInfo(name = "plato_id")
    private Integer idPlato;

    private Integer cantidad;

    private Double subTotal;

    @Ignore
    private Plato plato;

    public Integer getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Integer idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
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

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return plato.equals(that.plato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plato);
    }
}
