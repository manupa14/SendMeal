package com.example.sendmeal.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("plato_id")})
public class Plato implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plato_id")
    private Integer idPlato;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    private int imagen;
    private Boolean enOferta;

    public Plato() { }

    @Ignore
    public Plato(Integer idPlato, String titulo, String descripcion, Double precio, Integer calorias) {
        this.idPlato = idPlato;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.imagen = R.drawable.ic_launcher_background;
        this.enOferta = false;
    }

    @Ignore
    public Plato(Parcel in) {
        this.readFromParcel(in);
    }

    public Integer getIdPlato() {
        return idPlato;
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

   public  Boolean getEnOferta(){
        return enOferta;
   }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
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

    public void setEnOferta (Boolean enOferta){
        this.enOferta = enOferta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return imagen == plato.imagen &&
                titulo.equals(plato.titulo) &&
                descripcion.equals(plato.descripcion) &&
                precio == plato.precio &&
                calorias.equals(plato.calorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descripcion, precio, calorias, imagen);
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + idPlato +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                ", imagen=" + imagen +
                '}';
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){

        dest.writeInt(idPlato);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        dest.writeInt(imagen);

    }

    private void readFromParcel(Parcel in){

        idPlato = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
        imagen = in.readInt();

    }

    public static final Parcelable.Creator<Plato> CREATOR = new Parcelable.Creator<Plato>() {

        public Plato createFromParcel(Parcel in) {
            return new Plato(in);
        }

        public Plato[] newArray(int size) {
            return new Plato[size];
        }

    };



}

