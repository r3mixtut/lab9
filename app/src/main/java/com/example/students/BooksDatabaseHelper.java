package com.example.students;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDatabaseHelper extends SQLiteOpenHelper {
    private static final  String DB_NAME = "Author";
    private static final int DB_VERSION = 1;
    public BooksDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlText = "CREATE TABLE Authors (\n" +
                "id                 INTEGER     PRIMARY KEY AUTOINCREMENT, \n" +
                "author             TEXT (20)   NOT NULL, \n" +
                "genre              TEXT (20), \n" +
                "countryOfIssue     INTEGER, \n" +
                "criticallyTestedFlg BOOLEAN, \n" +
                "onSaleFlg           BOOLEAN\n" +
                ");";
        sqLiteDatabase.execSQL(sqlText);
        populateDB(sqLiteDatabase);
    }

    private void populateDB(SQLiteDatabase db){
        for(Books_genre books_genre: Books_genre.getGenreList()){
            insertRow(db, books_genre);
        }
    }
    private void insertRow(SQLiteDatabase db, Books_genre books_genre){
        ContentValues contentValues = new ContentValues();
        contentValues.put("author", books_genre.getAuthor());
        contentValues.put("genre", books_genre.getGenre());
        contentValues.put("countryOfIssue", books_genre.getCountryOfIssue());
        contentValues.put("criticallyTestedFlg", books_genre.isCriticallyTestedFlg());
        contentValues.put("onSaleFlg", books_genre.isOnSaleFlg());
        db.insert("Authors", null, contentValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
