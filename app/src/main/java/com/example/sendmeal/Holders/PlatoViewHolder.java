package com.example.sendmeal.Holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sendmeal.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PlatoViewHolder extends RecyclerView.ViewHolder
{
    CardView cv;
    ImageView imagen;
    TextView titulo;
    TextView precio;
    Button oferta;
    Button editar;
    Button quitar;

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getPrecio() {
        return precio;
    }

    public void setPrecio(TextView precio) {
        this.precio = precio;
    }

    public Button getOferta() {
        return oferta;
    }

    public void setOferta(Button oferta) {
        this.oferta = oferta;
    }

    public Button getEditar() {
        return editar;
    }

    public void setEditar(Button editar) {
        this.editar = editar;
    }

    public Button getQuitar() {
        return quitar;
    }

    public void setQuitar(Button quitar) {
        this.quitar = quitar;
    }

    public PlatoViewHolder(View itemView)
    {
        super(itemView);
        cv = itemView.findViewById(R.id.cvFilaPlato);
        imagen = itemView.findViewById(R.id.imgFilaPlato);
        titulo = itemView.findViewById(R.id.txtTituloPlato);
        precio = itemView.findViewById(R.id.txtPrecioPlato);
        oferta = itemView.findViewById(R.id.btnOferta);
        editar = itemView.findViewById(R.id.btnEditar);
        quitar = itemView.findViewById(R.id.btnQuitar);

    }



}