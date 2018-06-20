package com.example.croxas.wow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Croxas on 18/12/17.
 */

public class CustomListAdapterShop extends ArrayAdapter<Objeto> {

    private final Activity context;
    private final List<Objeto> listItems;
    private AlertDialog.Builder builder;
    SharedPreferences pref = getContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
    private Objeto oro;

    public CustomListAdapterShop(Activity context, List<Objeto> listItems) {
        super(context, R.layout.row, listItems);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        this.context=context;
        this.listItems=listItems;
        Gson gson = new Gson();
        String json = pref.getString("oro", "");
        this.oro = gson.fromJson(json, Objeto.class);

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView nombreItem = (TextView) rowView.findViewById(R.id.textviewNombre);
        TextView descripcionItem = (TextView) rowView.findViewById(R.id.textviewDescripcion);
        TextView precioItem = rowView.findViewById(R.id.textviewPrecio);


        Picasso.with(getContext()).load(listItems.get(position).getUrlIcon()).into(imageView);
        if (position != 0){
            nombreItem.setText(listItems.get(position).getNombre());
        }else{
            nombreItem.setText("Tus "+listItems.get(position).getNombre());
        }
        descripcionItem.setText("Descripcion: "+listItems.get(position).getDescripcion());
        if (position != 0){
            precioItem.setText("Precio: "+listItems.get(position).getCoste()+ " monedas de oro");
        }else{
            precioItem.setText("Tienes: "+listItems.get(position).getCantidad()+ " monedas de oro para gastar");
        }


        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (position != 0) {
                    builder.setTitle("Comprar "+listItems.get(position).getNombre()+"?")
                            .setMessage("Esto le costara un total de "+listItems.get(position).getCoste()+ " monedas de oro")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (oro.getCantidad() - listItems.get(position).getCoste() >= 0){
                                        oro.setCantidad(oro.getCantidad()-listItems.get(position).getCoste());
                                        listItems.get(0).setCantidad(oro.getCantidad());
                                        Gson gson = new Gson();
                                        String json = gson.toJson(oro);
                                        pref.edit().putString("oro", json).commit();
                                        json = pref.getString("objetos", "");
                                        Type listType = new TypeToken<ArrayList<Objeto>>(){}.getType();
                                        ArrayList<Objeto> misObjetos = gson.fromJson(json, listType);
                                        misObjetos.add(listItems.get(position));
                                        json = gson.toJson(misObjetos);
                                        pref.edit().putString("objetos", json).commit();
                                        notifyDataSetChanged();
                                    }else{
                                        new AlertDialog.Builder(context).setTitle("Insuficientes monedas de oro!").show();
                                    }


                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(R.drawable.chest_close);
                    builder.show();
                }

            }

        });

        return rowView;

    }
}
