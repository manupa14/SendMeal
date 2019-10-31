package com.example.sendmeal.Persistence;

import android.content.Context;
import android.widget.Toast;

import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PedidoRepository {

    private static PedidoRepository _INSTANCE = null;
    private PedidoDao pedidoDao;
    private PedidoRest pedidoRest;
    private Context ctx;


    private PedidoRepository(Context ctx){
        this.ctx = ctx;
        pedidoDao = DBClient.getInstance(ctx).getAppDataBase().pedidoDao();
        pedidoRest = MyRetrofit.getInstance().crearPedidoRest();
    }

    public static PedidoRepository getInstance(Context ctx){
        if(_INSTANCE == null) _INSTANCE = new PedidoRepository(ctx);
        return _INSTANCE;
    }

    public PedidoDao getPedidoDao(){
        return pedidoDao;
    }

    public void guardarPedidoEnviado(Pedido p){
        Call<Pedido> llamada = this.pedidoRest.crear(p);
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ctx, R.string.datosGuardados, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloEnviarPedido, Toast.LENGTH_SHORT).show();
            }
        });
    }

}