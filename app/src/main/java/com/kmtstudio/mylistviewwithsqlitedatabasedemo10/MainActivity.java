package com.kmtstudio.mylistviewwithsqlitedatabasedemo10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText idEdtTxt,nameTxt;
    private Button saveBtn,showBtn,updateBtn,deleteBtn;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        dbHelper = new DbHelper(this);

        idEdtTxt = findViewById(R.id.iDEditTxt);
        nameTxt = findViewById(R.id.nameEditTxt);

        saveBtn = findViewById(R.id.saveBtnID);
        showBtn = findViewById(R.id.showBtnID);
        updateBtn = findViewById(R.id.upDateBtnID);
        deleteBtn = findViewById(R.id.deleteBtnBtnID);


        saveBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String id = idEdtTxt.getText().toString();
        String name = nameTxt.getText().toString();


        if (v.getId() == R.id.saveBtnID) {

            if (id.equals("") && name.equals("")) {

                Toast.makeText(getApplicationContext(),"Please enter some data and try again",Toast.LENGTH_SHORT).show();

            } else {

                long rowNumber = dbHelper.insertData(id,name);

                if (rowNumber>-1) {

                    Toast.makeText(getApplicationContext(),"Data insert successful",Toast.LENGTH_SHORT).show();
                    idEdtTxt.setText("");
                    nameTxt.setText("");

                } else {

                    Toast.makeText(getApplicationContext(),"Sorry failed to insert data",Toast.LENGTH_SHORT).show();

                }
            }

        } else if (v.getId() == R.id.showBtnID) {

            Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(intent);


        } else if (v.getId() == R.id.upDateBtnID) {

            boolean isUpdated = dbHelper.updateData(id,name);

            if (isUpdated) {

                Toast.makeText(getApplicationContext(),"Data update successful",Toast.LENGTH_SHORT).show();
                idEdtTxt.setText("");
                nameTxt.setText("");

            } else {

                Toast.makeText(getApplicationContext(),"Failed to update the data",Toast.LENGTH_SHORT).show();

            }

        } else if (v.getId() == R.id.deleteBtnBtnID) {

            int value = dbHelper.deleteData(id);


            if (value>0) {

                Toast.makeText(getApplicationContext(),"Data delete successful",Toast.LENGTH_SHORT).show();
                idEdtTxt.setText("");

            } else {

                Toast.makeText(getApplicationContext(),"Sorry there was no data found to delete",Toast.LENGTH_SHORT).show();

            }

        }
    }
}