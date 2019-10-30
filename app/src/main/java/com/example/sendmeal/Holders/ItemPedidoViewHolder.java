package com.example.sendmeal.Holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sendmeal.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemPedidoViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView imgPlato;
    TextView txtTitulo;
    TextView txtPrecio;
    TextView txtCantidad;
    Button btnQuitar;
    Button btnMas;
    Button btnMenos;


    public ItemPedidoViewHolder(View itemView)
    {
        super(itemView);
        cv = itemView.findViewById(R.id.cvFilaPedido);
        imgPlato = itemView.findViewById(R.id.imgFilaPedido);
        txtTitulo = itemView.findViewById(R.id.txtTituloItem);
        txtPrecio = itemView.findViewById(R.id.txtPrecioItem);
        txtCantidad = itemView.findViewById(R.id.txtCantidad);
        btnQuitar = itemView.findViewById(R.id.btnQuitarItem);
        btnMas = itemView.findViewById(R.id.btnMas);
        btnMenos = itemView.findViewById(R.id.btnMenos);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getImgPlato() {
        return imgPlato;
    }

    public void setImgPlato(ImageView imgPlato) {
        this.imgPlato = imgPlato;
    }

    public TextView getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(TextView txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public TextView getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(TextView txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public TextView getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(TextView txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public Button getBtnMas() {
        return btnMas;
    }

    public void setBtnMas(Button btnMas) {
        this.btnMas = btnMas;
    }

    public Button getBtnMenos() {
        return btnMenos;
    }

    public void setBtnMenos(Button btnMenos) {
        this.btnMenos = btnMenos;
    }

    public Button getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(Button btnQuitar) {
        this.btnQuitar = btnQuitar;
    }

}
