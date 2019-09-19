package com.example.sendmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {

    private List<Plato> listaPlato;
    private Context context;

    public PlatoAdapter(List<Plato> listaPlato)
    {
        this.listaPlato = listaPlato;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder myHolder = new PlatoViewHolder(view);
        context = parent.getContext();
        return myHolder;
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, final int position) {

        Plato myPlato = listaPlato.get(position);

        holder.imagen.setImageResource(myPlato.getImagen());
        holder.titulo.setText(myPlato.getTitulo());
        holder.precio.setText(myPlato.getPrecio().toString());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlato.size();
    }

    public class PlatoViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        ImageView imagen;
        TextView titulo;
        TextView precio;

        public PlatoViewHolder(View itemView)
        {
            super(itemView);
            cv = itemView.findViewById(R.id.cvFilaPlato);
            imagen = itemView.findViewById(R.id.imgFilaPlato);
            titulo = itemView.findViewById(R.id.txtTituloPlato);
            precio = itemView.findViewById(R.id.txtPrecioPlato);
        }

    }

}
