package com.example.sendmeal.Persistence;

import com.example.sendmeal.Domain.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoRest {

    @GET("pedidos/")
    Call<List<Pedido>> buscarTodos();

    @DELETE("pedidos/{id}")
    Call<Void> borrar(@Path("id") Integer id);

    @PUT("pedidos/{id}")
    Call<Pedido> actualizar(@Path("id") Integer id, @Body Pedido pedido);

    @POST("pedidos/")
    Call<Pedido> crear(@Body Pedido pedido);

}
