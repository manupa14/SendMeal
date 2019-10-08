package com.example.sendmeal.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sendmeal.Activities.AltaPlato;
import com.example.sendmeal.Activities.ListaPlatos;
import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.Holders.PlatoViewHolder;
import com.example.sendmeal.IntentServices.MyIntentService;
import com.example.sendmeal.R;
import com.example.sendmeal.Receivers.MyReceiver;

import java.security.Provider;
import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    private List<Plato> listaPlato;
    private Context contexto;
    private static final int CODIGO_EDITAR_PLATO = 1;

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

                listaPlato.get(position).setEnOferta(true);

                /*Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(10000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //Enviamos el mensaje de Broadcast
                        Intent i = new Intent();
                        i.putExtra("Titulo", contexto.getString(R.string.tituloNotificacion));
                        i.putExtra("Mensaje", contexto.getString(R.string.mensajeNotificacion));
                        i.setAction(MyReceiver.EVENTO_OFERTAR);
                        contexto.sendBroadcast(i);
                    }
                };
                Thread t1 = new Thread(myRunnable);
                t1.start();*/
                //Implementado mediante IntentService por Juan 05/10/2019
               Intent nuevoServicio = new Intent(contexto,MyIntentService.class);
               nuevoServicio.putExtra("idPlatoSeleccionado",position);
               contexto.startService(nuevoServicio);

            }
        });

        holder.getEditar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(contexto, AltaPlato.class);
                i.putExtra("idPlatoSeleccionado", position);
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


}