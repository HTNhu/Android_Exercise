package com.example.android_exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

//    public DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    DBHelper(Context context){
        super(context, "book_list",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Book(id integer primary key, "+" title text, author text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Book");
        onCreate(sqLiteDatabase);
    }
    public boolean InsertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", book.getId());
        contentValues.put("title", book.getTitle());
        contentValues.put("author", book.getAuthor());
        db.insert("Book",null, contentValues);
        return true;
    }
    public Book getBook(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book where id=" + id,null);
        if(cursor != null)  cursor.moveToFirst();
        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return book;

    }
    public ArrayList<Book> getAllBook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Book", null);
        if(cursor != null) cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;

    }
    public boolean deleteBook(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("delete from Book where id=" + id,null);
        if(cursor != null)  cursor.moveToFirst();
//        Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return true;

    }

}
