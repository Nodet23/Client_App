package com.example.croxas.wow;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Croxas on 19/6/18.
 */

public class CustomListAdapterBag extends ArrayAdapter<Objeto> {

    private final Activity context;
    private final List<Objeto> listItems;

    public CustomListAdapterBag(Activity context, List<Objeto> listItems) {
        super(context, R.layout.row_bag, listItems);

        this.context=context;
        this.listItems=listItems;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_bag, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconBag);
        TextView nombreItem = (TextView) rowView.findViewById(R.id.textviewNombreBag);
        TextView descripcionItem = (TextView) rowView.findViewById(R.id.textviewDescripcionBag);
        TextView cantidadItem = rowView.findViewById(R.id.textviewCantidadBag);


        Picasso.with(getContext()).load(listItems.get(position).getUrlIcon()).into(imageView);
        nombreItem.setText(listItems.get(position).getNombre());
        descripcionItem.setText("Descripcion: "+listItems.get(position).getDescripcion());
        cantidadItem.setText("Cantidad: "+listItems.get(position).getCantidad()+ " en la mochila");

        /*rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("touched").show();
            }

        });*/

        return rowView;

    }
}