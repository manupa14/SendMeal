package com.example.sendmeal.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sendmeal.Activities.AltaPedido;
import com.example.sendmeal.Activities.AltaPlato;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Holders.PlatoViewHolder;
import com.example.sendmeal.IntentServices.MyIntentService;
import com.example.sendmeal.Persistence.PedidoRepository;
import com.example.sendmeal.R;


import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    private List<Plato> listaPlato;
    private Context contexto;
    private static final int CODIGO_EDITAR_PLATO = 1;
    private static final int _LISTA_PLATOS = 0;
    private static final int _BUSCAR_PLATO = 1;
    private int flag;

    public PlatoAdapter(List<Plato> listaPlato, Context context, int flag)
    {
        this.listaPlato = listaPlato;
        this.contexto = context;
        this.flag = flag;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder myHolder = new PlatoViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final PlatoViewHolder holder, final int position) {

        Plato myPlato = listaPlato.get(position);
        String pr = "$".concat(myPlato.getPrecio().toString());

        holder.getImagen().setImageResource(myPlato.getImagen());
        holder.getTitulo().setText(myPlato.getTitulo());
        holder.getPrecio().setText(pr);

        switch (flag) {
            case _BUSCAR_PLATO:
                ocultarBotones(holder);
                holder.getDescripcion().setText(myPlato.getDescripcion());
                break;
        }

        holder.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(contexto,"The position is: "+position,Toast.LENGTH_SHORT).show();
                switch (flag) {
                    case _BUSCAR_PLATO:

                        Plato plato = listaPlato.get(position);
                        ItemPedido itemPedidoAux = new ItemPedido();
                        itemPedidoAux.setPlato(plato);

                        if(PedidoRepository.getInstance(contexto).getItemsPedido().contains(itemPedidoAux)) {
                            Toast.makeText(contexto, R.string.falloAgregarItem, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent i = new Intent(contexto, AltaPedido.class);
                            i.putExtra("plato", plato);
                            i.putExtra("startedFrom","buscar");
                            i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                            contexto.startActivity(i);
                        }
                        break;

                }

            }
        });

        holder.getOfertar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Plato platoSeleccionado = listaPlato.get(position);
                platoSeleccionado.setEnOferta(true);

               Intent nuevoServicio = new Intent(contexto,MyIntentService.class);
               nuevoServicio.putExtra("platoSeleccionado",platoSeleccionado);
               contexto.startService(nuevoServicio);

            }
        });

        holder.getEditar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, AltaPlato.class);
                Plato platoSeleccionado = listaPlato.get(position);
                i.putExtra("platoSeleccionado", platoSeleccionado);
                i.putExtra("startedFrom","editar");
                ((Activity)contexto).startActivityForResult(i,CODIGO_EDITAR_PLATO);
            }
        });

        holder.getQuitar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder((Activity)contexto);
                builder.setTitle(R.string.tituloAlertDialogQuitar);
                builder.setMessage(R.string.mensajeAlertDialogQuitar);
                builder.setPositiveButton(R.string.btnAccept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listaPlato.remove(position);
                        Toast.makeText(contexto, R.string.platoBorrado, Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.btnCancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listaPlato.size();
    }

    private void ocultarBotones (PlatoViewHolder holder) {
        holder.getQuitar().setVisibility(View.INVISIBLE);
        holder.getOfertar().setVisibility(View.INVISIBLE);
        holder.getEditar().setVisibility(View.INVISIBLE);
        holder.getDescripcion().setVisibility(View.VISIBLE);
    }


}