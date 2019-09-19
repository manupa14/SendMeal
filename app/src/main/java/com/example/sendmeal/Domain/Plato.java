package com.example.sendmeal.Domain;

import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plato {

    private Integer id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    private int imagen;

    public Plato(Integer id, String titulo, String descripcion, Double precio, Integer calorias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.imagen = R.drawable.ic_launcher_background;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public int getImagen() {
        return imagen;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return imagen == plato.imagen &&
                id.equals(plato.id) &&
                titulo.equals(plato.titulo) &&
                descripcion.equals(plato.descripcion) &&
                precio.equals(plato.precio) &&
                calorias.equals(plato.calorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, precio, calorias, imagen);
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                ", imagen=" + imagen +
                '}';
    }

}

