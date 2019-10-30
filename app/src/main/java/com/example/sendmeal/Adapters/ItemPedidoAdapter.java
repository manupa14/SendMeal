package com.example.sendmeal.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Holders.ItemPedidoViewHolder;
import com.example.sendmeal.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemPedidoAdapter extends RecyclerView.Adapter<ItemPedidoViewHolder> {

    private List<ItemPedido> listaItemsPedido;
    private Context context;


    public ItemPedidoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ItemPedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pedido, parent, false);
        ItemPedidoViewHolder myHolder = new ItemPedidoViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(ItemPedidoViewHolder holder, final int position) {

        ItemPedido itemPedido = listaItemsPedido.get(position);
        String precio = "$".concat(myPlato.getPrecio().toString());

        holder.getImgPlato().setImageResource(myPlato.getImagen());
        holder.getTxtTitulo().setText(myPlato.getTitulo());
        holder.getTxtPrecio().setText(precio);

        holder.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(contexto,"The position is: "+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaPlato.size();
    }


}
