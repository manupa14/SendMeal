package com.example.sendmeal.Dao.Rest;
import com.example.sendmeal.Domain.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PlatoRest {

    @GET("platos/")
    Call<List<Plato>> buscarTodos();

    @GET("platos/")
    Call<List<Plato>> buscarPorNombre(@Query("titulo") String titulo);

    @DELETE("platos/{id}")
    Call<Void> borrar(@Path("id") Integer id);

    @PUT("platos/{id}")
    Call<Plato> actualizar(@Path("id") Integer id, @Body Plato plato);

    @POST("platos/")
    Call<Plato> crear(@Body Plato plato);




}
