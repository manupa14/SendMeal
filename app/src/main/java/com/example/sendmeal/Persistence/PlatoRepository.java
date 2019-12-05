package com.example.sendmeal.Persistence;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.sendmeal.Domain.Plato;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatoRepository {

    private static PlatoRepository _INSTANCE = null;
    public static final int _BUSCAR_PLATOS_TODOS = 0;
    public static final int _BUSCAR_PLATOS = 1;
    public static final int _ACCTUALIZAR_PLATO=2;
    private Context ctx;
    private PlatoDao platoDao;
    private PlatoRest platoRest;
    private List<Plato> listaPlatos = new ArrayList<>();


    private PlatoRepository(Context ctx){
        this.ctx = ctx;
        platoDao = DBClient.getInstance(ctx).getAppDataBase().platoDao();
        platoRest = MyRetrofit.getInstance().crearPlatoRest();
    }

    public static PlatoRepository getInstance(Context ctx){
        if(_INSTANCE == null) _INSTANCE = new PlatoRepository(ctx);
        return _INSTANCE;
    }

    public void crearPlato(Plato p){

        Call<Plato> llamada = this.platoRest.crear(p);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ctx, R.string.datosGuardados, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloCrear, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buscarPlatosPorNombre(final Handler h, String nombre){
        Call<List<Plato>> llamada = this.platoRest.buscarPorNombre(nombre);
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _BUSCAR_PLATOS;
                    h.sendMessage(m);
                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloBuscar, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Plato> getListaPlatos(){
        return listaPlatos;
    }

    public List<Plato> buscarTodos(final Handler h){
        Call<List<Plato>> llamada = this.platoRest.buscarTodos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _BUSCAR_PLATOS_TODOS;
                    h.sendMessage(m);
                }
            }
            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloCrear, Toast.LENGTH_SHORT).show();
            }
        });
        return listaPlatos;
    }

    public void actualizarPlato(final Plato plato) {
        Call<Plato> llamada = this.platoRest.actualizar(plato.getId(),plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {

                if(response.isSuccessful()){
                    listaPlatos.remove(plato);
                    listaPlatos.add(response.body());
                }

            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloEditar,Toast.LENGTH_SHORT).show();
            }
        });

    }
}