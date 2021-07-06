package com.kmtstudio.mylistviewwithsqlitedatabasedemo10;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        dbHelper = new DbHelper(this);

        listView = findViewById(R.id.listViewID);


        loadData();


    }

    private void loadData() {

        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = dbHelper.showData();


        if (cursor.getCount()==0) {

            Toast.makeText(getApplicationContext(),"Sorry there is no data found in database",Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {

                listData.add(cursor.getString(0)+" \t "+cursor.getString(1));

            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.textViewID,listData);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedValue = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Selected value : "+selectedValue,Toast.LENGTH_SHORT).show();


        });
    }
}