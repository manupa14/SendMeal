package com.example.sendmeal.Domain;

import java.util.Date;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("pedido_id")})
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pedido_id")
    private long idPedido;

    //1.PENDIENTE, 2.ENVIADO, 3.ACEPTADO, 4.RECHAZADO, 5.EN_PREPARACION, 6.EN_ENVIO, 7.ENTREGADO, 8.CANCELADO
    private Integer estado;
    private Date fecha;
    private Double latitud;
    private Double longitud;
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Ignore
    private List<ItemPedido> items;

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
