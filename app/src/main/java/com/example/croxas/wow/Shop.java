package com.example.croxas.wow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    ListView listView;
    Objeto oro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        listView = (ListView) findViewById(R.id.listviewBag);

        Bundle extras = getIntent().getBundleExtra("bundle");
        ArrayList<Objeto> bag = (ArrayList<Objeto>) extras.getSerializable("bag");

        CustomListAdapterShop adapter=new CustomListAdapterShop(Shop.this, bag);
        listView.setAdapter(adapter);
    }


}
