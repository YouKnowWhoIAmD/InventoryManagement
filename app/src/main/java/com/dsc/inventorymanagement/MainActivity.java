package com.dsc.inventorymanagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AbsListView;

import com.dsc.inventorymanagement.DataBases.DbHelper;
import com.dsc.inventorymanagement.adapterClasses.CustomAdapter;
import com.dsc.inventorymanagement.dataStorers.Items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InnerActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.emptyTextView);
        listView.setEmptyView(emptyView);

        final ArrayList<Items> list = dbHelper.retrieveFromDB();

        adapter = new CustomAdapter(this,R.layout.list_item,list);
        listView.setAdapter(adapter);


    }

    public void clicked(int id){
        Intent intent = new Intent(this, InnerActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }
}
