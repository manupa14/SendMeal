package com.example.sendmeal.Domain;

import android.graphics.Bitmap;
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
    private int id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    private String imagen;
    private Boolean enOferta;


    public Plato(){

    }

    @Ignore
    public Plato(Parcel in) {
        this.readFromParcel(in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public  Boolean getEnOferta(){
        return enOferta;
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

    public void setImagen(String imagen) {
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
        return id == plato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeInt(calorias);
        dest.writeDouble(precio);
        dest.writeString(imagen);

    }

    private void readFromParcel(Parcel in){
        id = in.readInt();
        titulo = in.readString();
        descripcion = in.readString();
        calorias = in.readInt();
        precio = in.readDouble();
        imagen = in.readString();

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

