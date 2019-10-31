package com.example.sendmeal.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Holders.ItemPedidoViewHolder;
import com.example.sendmeal.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemPedidoAdapter extends RecyclerView.Adapter<ItemPedidoViewHolder> {

    private List<ItemPedido> itemsPedido;
    private Context context;


    public ItemPedidoAdapter(List<ItemPedido> itemsPedido, Context context) {
        this.itemsPedido = itemsPedido;
        this.context = context;
    }

    @Override
    public ItemPedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pedido, parent, false);
        ItemPedidoViewHolder myHolder = new ItemPedidoViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final ItemPedidoViewHolder holder, final int position) {

        ItemPedido itemPedido = itemsPedido.get(position);
        String precio = "$".concat(itemPedido.getSubTotal().toString());

        holder.getImgPlato().setImageResource(itemPedido.getPlato().getImagen());
        holder.getTxtTitulo().setText(itemPedido.getPlato().getTitulo());
        holder.getTxtPrecio().setText(precio);

        holder.getBtnMas().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidadNueva = Integer.parseInt(holder.getTxtCantidad().getText().toString()) + 1;
                holder.getTxtCantidad().setText(cantidadNueva);

                itemsPedido.get(position).setCantidad(cantidadNueva);

                //TODO Ver como incrementar el sub total
            }
        });

        holder.getBtnMenos().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cantidadActual = Integer.parseInt(holder.getTxtCantidad().getText().toString());

                if(cantidadActual > 0){
                    int cantidadNueva = cantidadActual - 1;
                    holder.getTxtCantidad().setText(cantidadNueva);

                    itemsPedido.get(position).setCantidad(cantidadNueva);
                }

                //TODO Ver como disminuir el sub total

            }
        });

        holder.getBtnQuitar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsPedido.remove(position);
                Toast.makeText(context, R.string.itemPedidoQuitado, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsPedido.size();
    }


}
