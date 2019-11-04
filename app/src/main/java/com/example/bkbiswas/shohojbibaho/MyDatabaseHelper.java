package com.example.bkbiswas.shohojbibaho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="user.db";
    private static final int VERSION_NO=3;
    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,VERSION_NO);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context,"On Create",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("CREATE TABLE INFO(e VARCHAR(1000),p VARCHAR(1000), f VARCHAR(1000), l VARCHAR(1000), g VARCHAR(1000), d VARCHAR(1000), m VARCHAR(1000), y VARCHAR(1000), r VARCHAR(1000), c VARCHAR(1000), w VARCHAR(1000), b VARCHAR(1000), hf VARCHAR(1000), hi VARCHAR(1000), cn VARCHAR(1000), wa VARCHAR(1000), des VARCHAR(1000), username VARCHAR(1000));");
        }catch (Exception e)
        {
            Toast.makeText(context,"Something went wrong!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context,"On Upgrade",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS INFO ;");
            onCreate(sqLiteDatabase);
        }catch (Exception e)
        {
            Toast.makeText(context,"Something went wrong!",Toast.LENGTH_LONG).show();
        }


    }

    public long insert(String e, String p, String f, String l, String g, String d, String m, String y, String w, String b, String hf, String hi, String r, String cn, String c, String wa, String des, String username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("e", e);
        contentValues.put("p", p);
        contentValues.put("f", f);
        contentValues.put("l", l);
        contentValues.put("g", g);
        contentValues.put("d", d);
        contentValues.put("m", m);
        contentValues.put("y", y);
        contentValues.put("w", w);
        contentValues.put("b", b);
        contentValues.put("hf", hf);
        contentValues.put("hi", hi);
        contentValues.put("r", r);
        contentValues.put("cn", cn);
        contentValues.put("c", c);
        contentValues.put("wa", wa);
        contentValues.put("des", des);
        contentValues.put("username",username);
        return sqLiteDatabase.insert("INFO",null,contentValues);
    }

    public Cursor getINFO(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM INFO ;",null);
    }

    public boolean updatehis(String e, String p, String f, String l, String g, String d, String m, String y, String w, String b, String hf, String hi, String r, String cn, String c, String wa, String des, String uname){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("e", e);
        contentValues.put("p", p);
        contentValues.put("f", f);
        contentValues.put("l", l);
        contentValues.put("g", g);
        contentValues.put("d", d);
        contentValues.put("m", m);
        contentValues.put("y", y);
        contentValues.put("w", w);
        contentValues.put("b", b);
        contentValues.put("hf", hf);
        contentValues.put("hi", hi);
        contentValues.put("r", r);
        contentValues.put("cn", cn);
        contentValues.put("c", c);
        contentValues.put("wa", wa);
        contentValues.put("des", des);
        contentValues.put("username", uname);
        sqLiteDatabase.update("INFO",contentValues,"username",new String[]{"abc"});
        return true;
    }

    public boolean updateUser(String username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        sqLiteDatabase.update("INFO",contentValues,"username",new String[]{"abc"});
        return true;
    }

}