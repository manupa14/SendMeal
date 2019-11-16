package com.example.sendmeal.Persistence;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.sendmeal.Activities.MapPedidos;
import com.example.sendmeal.Domain.ItemPedido;
import com.example.sendmeal.Domain.Pedido;
import com.example.sendmeal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PedidoRepository {

    private static PedidoRepository _INSTANCE = null;
    private PedidoDao pedidoDao;
    private PedidoRest pedidoRest;
    private Context ctx;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<ItemPedido> itemsPedido = new ArrayList<>();

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

    public List<ItemPedido> getItemsPedido () {
        return itemsPedido;
    }

    public void setItemsPedido (List<ItemPedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
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

    public void listarPedidosEnviados(final Handler h){
        Call<List<Pedido>> llamada = this.pedidoRest.buscarTodos();
        llamada.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.isSuccessful()){
                    pedidos.clear();
                    pedidos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = MapPedidos.BUSCAR_PEDIDOS_ENVIADOS;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(ctx, R.string.falloBuscarPedidos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<Pedido> getPedidos(){
        return pedidos;
    }

}