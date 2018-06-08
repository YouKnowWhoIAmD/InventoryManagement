package com.dsc.inventorymanagement.DataBases;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.dsc.inventorymanagement.dataStorers.Items;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public static final String TABLE_NAME = "list";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_IMAGE = "image";
    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT NOT NULL," +
            COLUMN_PRICE + " TEXT NOT NULL," +
            COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
            COLUMN_IMAGE + " TEXT NOT NULL" + ");";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertIntoDB(Items item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,item.getName());
        values.put(COLUMN_PRICE,item.getPrice());
        values.put(COLUMN_QUANTITY,item.getQuantity());
        values.put(COLUMN_IMAGE,item.getImage());
        long num = db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<Items> retrieveFromDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{_ID,COLUMN_NAME,COLUMN_PRICE,COLUMN_QUANTITY,COLUMN_IMAGE},null,null,null,null,null);
        ArrayList<Items> list = new ArrayList<Items>();
        if (cursor.moveToFirst()) {
            do {
                Items item = new Items(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Items> readOneItem(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{_ID,COLUMN_NAME,COLUMN_PRICE,COLUMN_QUANTITY,COLUMN_IMAGE},_ID + "=?",new String[]{String.valueOf(id)},null,null,null);
        ArrayList<Items> list = new ArrayList<Items>();
        if (cursor.moveToFirst()) {
            do {
                Items item = new Items(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void update(long id,int quantity){

    }

}
