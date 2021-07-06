package com.kmtstudio.mylistviewwithsqlitedatabasedemo10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final int VERSION_CODE = 1;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + NAME + " VARCHAR(255) NOT NULL)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context,"onCreate is called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {

            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        } catch (Exception e) {

            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }

    }

    public long insertData(String id, String name) {

        SQLiteDatabase liteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);

        return liteDatabase.insert(TABLE_NAME,null,values);

    }

    public Cursor showData() {

        SQLiteDatabase liteDatabase = this.getWritableDatabase();

        return liteDatabase.rawQuery(SELECT_ALL,null);

    }

    public boolean updateData(String id, String name) {

        SQLiteDatabase liteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);

        liteDatabase.update(TABLE_NAME,values,ID+" = ?",new String[]{id});

        return true;

    }

    public int deleteData(String id) {

        SQLiteDatabase liteDatabase = this.getWritableDatabase();

        return liteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});

    }
}
