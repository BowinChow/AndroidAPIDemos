package com.example.androiddemo.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androiddemo.utils.ContextUtils;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";
    private Context mContext;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "already create a database", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);
    }

    public void addData() {
        Log.e(ContextUtils.TAG, "add data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Shengmin");
        contentValues.put("author", "dan");
        contentValues.put("pages", 456);
        contentValues.put("price", 17);
        db.insert("Book", null, contentValues);
        contentValues.clear();
        contentValues.put("name", "david");
        contentValues.put("author", "kay");
        contentValues.put("pages", 522);
        contentValues.put("price", 16);
        db.insert("Book", null, contentValues);
    }

    public void dataQuery() {
        Log.e(ContextUtils.TAG, "do query");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                Log.e(ContextUtils.TAG, "the query result: name->" + name + " author->" + author);
            } while (cursor.moveToNext());

        }

        cursor.close();
    }
}
