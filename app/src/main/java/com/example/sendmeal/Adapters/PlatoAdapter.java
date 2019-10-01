package com.example.sendmeal.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sendmeal.Activities.AltaPlato;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Holders.PlatoViewHolder;
import com.example.sendmeal.R;

import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    private List<Plato> listaPlato;
    private Context contexto;
    private static final int CODIGO_OFERTAR_PLATO = 1;
    private static final int CODIGO_EDITAR_PLATO = 2;
    private static final int CODIGO_QUITAR_PLATO = 3;

    public PlatoAdapter(List<Plato> listaPlato, Context context)
    {
        this.listaPlato = listaPlato;
        this.contexto = context;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder myHolder = new PlatoViewHolder(view);
        //context = parent.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, final int position) {

        Plato myPlato = listaPlato.get(position);
        String pr = "$".concat(myPlato.getPrecio().toString());

        holder.getImagen().setImageResource(myPlato.getImagen());
        holder.getTitulo().setText(myPlato.getTitulo());
        holder.getPrecio().setText(pr);

        holder.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(contexto,"The position is: "+position,Toast.LENGTH_SHORT).show();
            }
        });

        holder.getOfertar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.getEditar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, AltaPlato.class);
                i.putExtra("platoSeleccionado", position);
                ((Activity)contexto).startActivityForResult(i,CODIGO_EDITAR_PLATO);
            }
        });

        holder.getQuitar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaPlato.size();
    }

}
