package com.example.croxas.wow;

/**
 * Created by Croxas on 20/12/17.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("Jugador/{email}")
    Call<Jugador> getLogin(@Path("email") String user, @Body Login loginBody);
    @POST("newJugador/{email}")
    Call<Jugador> getNouJugador(@Path("email")String user, @Body Login loginBody);
    @GET("Mapa")
    Call<String> getMapa();

    public static final String BASE_URL = "http://10.0.2.2:8080/myapp/";

    @GET("objetosPersonaje")
    Call<List<Objeto>> getObjPj();

    @GET("mapa")
    Call<List<EstadoMapa>> getMap();

    @POST("addMapa/{mapa}")
    Call<String> addM(@Query("mapa") String mapa);

    @POST("addItems/{items}")
    Call<String> addI(@Query("items") String items);

}
