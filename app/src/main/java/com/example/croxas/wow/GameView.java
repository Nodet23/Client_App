package com.example.croxas.wow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Croxas on 13/6/18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private com.example.croxas.wow.MainThread thread;
    private TierraSprite tierraSprite;
    private CharacterSprite characterSprite;
    private UpKeySprite upKeySprite;
    private DownKeySprite downKeySprite;
    private LeftKeySprite leftKeySprite;
    private RightKeySprite rightKeySprite;
    private AntagonistSprite antagonistSprite;
    private CoinSprite coinSprite;
    private BagSprite bagSprite;
    private GuardarSprite guardarSprite;
    private boolean collision = false;
    private AlertDialog.Builder builder;
    private ArrayList<Objeto> misObjetos;
    private ArrayList<Objeto> tiendaObjetos;
    private Objeto oro;
    private ApiService apiRest;
    private int opcion = 1;
    SharedPreferences pref = getContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

    //private long lastClick;


    public GameView(Context context, int opcion) {
        super(context);

        this.opcion = opcion;
        if (opcion == 0)
            misObjetos = new ArrayList<Objeto>();
        System.out.println("-----_______----- DESPUES!");
        tiendaObjetos =  new ArrayList<>();
        oro = new Objeto("Monedas de oro", "dinero", "Se puede cambiar por cosas la mar de utiles", 1, 1, 0, "https://cdn.discordapp.com/attachments/343127226578894849/458340168970010625/Pro_Coin_1.png");
        Gson gson = new Gson();
        String json = gson.toJson(oro);
        pref.edit().putString("oro", json).commit();
        if (opcion == 0)
            misObjetos.add(oro);
        json = gson.toJson(misObjetos);
        pref.edit().putString("objetos", json).commit();
        tiendaObjetos.add(oro);
        tiendaObjetos.add(new Objeto("Espada de madera", "arma", "Barata pero poco letal", 1, 1, 1, "https://vignette.wikia.nocookie.net/clubpenguin/images/3/3b/Espada_de_Madera_icono.png/revision/latest?cb=20141121011021&path-prefix=es"));
        tiendaObjetos.add(new Objeto("Escudo de metal", "escudo", "Es de segunda mano pero te protegera", 1, 2, 1, "https://orig00.deviantart.net/532b/f/2015/241/c/7/c72a346c7ab8487fb59171b58031c0c3-d3fkyap.png"));
        thread = new com.example.croxas.wow.MainThread(getHolder(), this);
        setFocusable(true);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRest = retrofit.create(ApiService.class);

        if(opcion == 1){
            misObjetos = new ArrayList<Objeto>();
            System.out.println("-----_______----- ANTES!");


            Call<List<Objeto>> getItems = apiRest.getObjPj();

            getItems.enqueue(new Callback<List<Objeto>>() {
                @Override
                public void onResponse(Call<List<Objeto>> call, Response<List<Objeto>> response) {
                    if (!response.isSuccessful()){
                        Log.i("TAG", "Error1 " + response.code());
                        System.out.println("-----_______----- FAILURE 1!");
                    }else {
                        System.out.println("-----_______----- GUAYYY!");
                        List<Objeto> objetosPj = response.body();
                        System.out.println("---___------ LISTA 0: "+objetosPj.get(0).getNombre());
                        misObjetos.addAll(objetosPj);
                        String json = new Gson().toJson(misObjetos);
                        pref.edit().putString("objetos", json).commit();
                        oro = misObjetos.get(0);
                        json = new Gson().toJson(oro);
                        pref.edit().putString("oro", json).commit();
                    }
                }

                @Override
                public void onFailure(Call<List<Objeto>> call, Throwable t) {
                    Log.e("TAG", "Error 2: " + t.getMessage());
                    System.out.println("-----_______----- FAILURE 2!");
                }
            });
        }

        if(opcion == 1){
            System.out.println("-----_______----- ANTES!");
            retrofit  = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiRest = retrofit.create(ApiService.class);

            Call<List<EstadoMapa>> getMapaI = apiRest.getMap();

            getMapaI.enqueue(new Callback<List<EstadoMapa>>() {
                @Override
                public void onResponse(Call<List<EstadoMapa>> call, Response<List<EstadoMapa>> response) {
                    if (!response.isSuccessful()){
                        Log.i("TAG", "Error1 " + response.code());
                        System.out.println("-----_______----- FAILURE 1!");
                    }else {
                        System.out.println("-----_______----- GUAYYY!");
                        List<EstadoMapa> estadoMapa = response.body();
                        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.prota), estadoMapa.get(0).getX(), estadoMapa.get(0).getY());
                        if (estadoMapa.get(1).isExist() == false){
                            coinSprite = null;
                        }else{
                            coinSprite = new CoinSprite(BitmapFactory.decodeResource(getResources(),R.drawable.coin_min), estadoMapa.get(1).getX(), estadoMapa.get(1).getY());
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<EstadoMapa>> call, Throwable t) {
                    Log.e("TAG", "Error 2: " + t.getMessage());
                    System.out.println("-----_______----- FAILURE 2!");
                }
            });
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Bienvenido a la tienda!")
                .setMessage("Quiere ver las mercancias?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), Shop.class);
                        Bundle args = new Bundle();
                        tiendaObjetos.get(0).setCantidad(oro.getCantidad());
                        args.putSerializable("bag", tiendaObjetos);
                        intent.putExtra("bundle", args);
                        getContext().startActivity(intent);                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(R.drawable.chest_close);

        tierraSprite = new TierraSprite(BitmapFactory.decodeResource(getResources(),R.drawable.tierra));
        if (opcion == 0)characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.prota));
        upKeySprite = new UpKeySprite(BitmapFactory.decodeResource(getResources(),R.drawable.arrow_keys));
        downKeySprite = new DownKeySprite(BitmapFactory.decodeResource(getResources(),R.drawable.arrow_keys));
        leftKeySprite = new LeftKeySprite(BitmapFactory.decodeResource(getResources(),R.drawable.arrow_keys));
        rightKeySprite = new RightKeySprite(BitmapFactory.decodeResource(getResources(),R.drawable.arrow_keys));
        antagonistSprite = new AntagonistSprite(BitmapFactory.decodeResource(getResources(),R.drawable.antagonista));
        if (opcion==0)coinSprite = new CoinSprite(BitmapFactory.decodeResource(getResources(),R.drawable.coin_min));
        bagSprite = new BagSprite(BitmapFactory.decodeResource(getResources(),R.drawable.bolsa));
        guardarSprite = new GuardarSprite(BitmapFactory.decodeResource(getResources(),R.drawable.guardar));

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        /*boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }*/
    }

    public void update() {
        //tierraSprite.update();
        //characterSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tierraSprite.draw(canvas);
        upKeySprite.draw(canvas);
        downKeySprite.draw(canvas);
        leftKeySprite.draw(canvas);
        rightKeySprite.draw(canvas);
        bagSprite.draw(canvas);
        guardarSprite.draw(canvas);
        antagonistSprite.draw(canvas);
        if (coinSprite != null)
            coinSprite.draw(canvas);


        characterSprite.draw(canvas);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
            synchronized (getHolder()) {
                Gson gson = new Gson();
                String json = pref.getString("objetos", "");
                Type listType = new TypeToken<ArrayList<Objeto>>(){}.getType();
                misObjetos = gson.fromJson(json, listType);
                json = pref.getString("oro", "");
                this.oro = gson.fromJson(json, Objeto.class);
                misObjetos.get(0).setCantidad(oro.getCantidad());
                // Retocar las esquinas para ajustar rango de colision
                // Interfaz sprite y pasar vector con todos los sprites para comprobar colisiones en el objeto
                // Cuidado con objetos que esten pegados
                if (bagSprite.isCollition(event.getX(), event.getY())) {
                    Intent intent = new Intent(getContext(), Bag.class);
                    Bundle args = new Bundle();
                    args.putSerializable("bag", misObjetos);
                    intent.putExtra("bundle", args);
                    getContext().startActivity(intent);
                }

                if (guardarSprite.isCollition(event.getX(), event.getY())) {

                    Gson gsonB = new GsonBuilder()
                            .setLenient()
                            .create();
                    String addI = gsonB.toJson(misObjetos);
                    Call<String> postItems = apiRest.addI(addI);
                    postItems.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.println("Dentro Response");
                            if (!response.isSuccessful()){
                                System.out.println("Failure 1: "+response.code());
                                Log.i("TAG", "Error 1 " + response.code());
                            }else {

                                String response1 = response.body();
                                new AlertDialog.Builder(getContext()).setTitle(response1).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println("Failure 2");
                            Log.e("TAG", "Error 2 : " + t.getMessage());
                        }
                    });

                    gsonB = new GsonBuilder()
                            .setLenient()
                            .create();
                    ArrayList<EstadoMapa> miMapa = new ArrayList<EstadoMapa>();
                    EstadoMapa estadoPersonaje = new EstadoMapa(characterSprite.x, characterSprite.y, true);
                    EstadoMapa estadoCoin = null;
                    boolean existCoin = false;
                    if (coinSprite != null) {
                        existCoin = true;
                        estadoCoin = new EstadoMapa(coinSprite.x, coinSprite.y, existCoin);
                    }else{
                        estadoCoin = new EstadoMapa(0, 0, existCoin);
                    }
                    miMapa.add(estadoPersonaje);
                    miMapa.add(estadoCoin);
                    String addM = gsonB.toJson(miMapa);
                    Call<String> postMapa = apiRest.addM(addM);
                    postMapa.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.println("Dentro Response");
                            if (!response.isSuccessful()){
                                System.out.println("Failure 1: "+response.code());
                                Log.i("TAG", "Error 1 " + response.code());
                            }else {

                                String response1 = response.body();
                                new AlertDialog.Builder(getContext()).setTitle(response1).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            System.out.println("Failure 2");
                            Log.e("TAG", "Error 2 : " + t.getMessage());
                        }
                    });
                }

                if (upKeySprite.isCollition(event.getX(), event.getY())) {
                    collision = false;
                    if (collision = antagonistSprite.collisionProta(0, characterSprite) == true){
                        builder.show();
                        return false;
                    }
                    if (coinSprite != null)
                        if (collision = coinSprite.collisionProta(0, characterSprite) == true)
                        {
                            coinSprite = null;
                            oro.setCantidad(oro.getCantidad()+1);
                            gson = new Gson();
                            json = gson.toJson(oro);
                            pref.edit().putString("oro", json).commit();
                            misObjetos.get(0).setCantidad(oro.getCantidad()+1);
                            return false;
                        }
                    characterSprite.moveUp(collision, 0);
                    //Toast.makeText(getContext(), "COLLISION!", Toast.LENGTH_SHORT).show();
                }
                if (downKeySprite.isCollition(event.getX(), event.getY())) {
                    collision = false;
                    if (collision = antagonistSprite.collisionProta(1, characterSprite) == true){
                        builder.show();
                        return false;
                    }
                    if (coinSprite != null)
                        if (collision = coinSprite.collisionProta(1, characterSprite) == true)
                        {
                            coinSprite = null;
                            oro.setCantidad(oro.getCantidad()+1);
                            gson = new Gson();
                            json = gson.toJson(oro);
                            pref.edit().putString("oro", json).commit();
                            misObjetos.get(0).setCantidad(oro.getCantidad()+1);
                            return false;
                        }
                    characterSprite.moveDown(collision, 0);
                }
                if (rightKeySprite.isCollition(event.getX(), event.getY())) {
                    collision = false;
                    if (collision = antagonistSprite.collisionProta(2, characterSprite) == true){
                        builder.show();
                        return false;
                    }
                    if (coinSprite != null)
                        if (collision = coinSprite.collisionProta(2, characterSprite) == true)
                        {
                            coinSprite = null;
                            oro.setCantidad(oro.getCantidad()+1);
                            gson = new Gson();
                            json = gson.toJson(oro);
                            pref.edit().putString("oro", json).commit();
                            misObjetos.get(0).setCantidad(oro.getCantidad()+1);
                            return false;
                        }
                    characterSprite.moveRight(collision, 0);
                }
                if (leftKeySprite.isCollition(event.getX(), event.getY())) {
                    collision = false;
                    if (collision = antagonistSprite.collisionProta(3, characterSprite) == true){
                        builder.show();
                        return false;
                    }
                    if (coinSprite != null)
                        if (collision = coinSprite.collisionProta(3, characterSprite) == true)
                        {
                            coinSprite = null;
                            oro.setCantidad(oro.getCantidad()+1);
                            gson = new Gson();
                            json = gson.toJson(oro);
                            pref.edit().putString("oro", json).commit();
                            misObjetos.get(0).setCantidad(oro.getCantidad()+1);
                            return false;
                        }
                    characterSprite.moveLeft(collision, 0);
                }
            }
        return false;
    }
}
