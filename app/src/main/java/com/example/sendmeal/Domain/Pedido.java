package com.example.sendmeal.Domain;

import java.util.Date;
import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @Ignore
    private Date fecha;
    //1.PENDIENTE, 2.ENVIADO, 3.ACEPTADO, 4.RECHAZADO, 5.EN_PREPARACION, 6.EN_ENVIO, 7.ENTREGADO, 8.CANCELADO
    private int estado;
    private double lat;
    private double lng;
    @Ignore
    private List<ItemPedido> items;

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }




}
