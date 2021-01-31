package com.example.students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import  android.content.Intent;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;

public class Book_List_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__list_);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Books_genre author = ((Books_genre) adapterView.getItemAtPosition(i));
                Intent intent =new Intent(Book_List_Activity.this, genreBooksActivity.class);
                intent.putExtra(BooksListActivity.AUTHOR, author.getId());
                startActivity(intent);
            }
        };
        ListView listView = (ListView) findViewById(R.id.author_list);
        listView.setOnItemClickListener(listener);
        ArrayAdapter<Books_genre> adapter = new ArrayAdapter<Books_genre>(
                this,
                android.R.layout.simple_list_item_1,
                Books_genre.getGenreList()
        );
        listView.setAdapter(adapter);
    }

    private ArrayList<Books_genre> getDataFromDB(){
        ArrayList<Books_genre> genreArrayList = new ArrayList<Books_genre>();
        SQLiteOpenHelper sqLiteHelper =  new BooksDatabaseHelper(this);
        try{
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            Cursor cursor = db.query("Authors",
                    new String[] {"author", "genre", "countryOfIssue", "criticallyTestedFlg", "onSaleFlg", "id"},
                    null, null, null, null, "author");
            while(cursor.moveToNext()){
                genreArrayList.add(
                  new Books_genre(
                          cursor.getInt(5),
                          cursor.getString(0),
                          cursor.getString(1),
                          cursor.getInt(2),
                          (cursor.getInt(3) > 0),
                          (cursor.getInt(4) > 0)
                  )
                );
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_LONG);
            toast.show();
        }
        return genreArrayList;
    }
    @Override
    protected void onStart() {
        super.onStart();
        getDataFromDB();
        ListView listView = (ListView) findViewById(R.id.author_list);
        ArrayAdapter<Books_genre> adapter = new ArrayAdapter<Books_genre>(
          this,
                android.R.layout.simple_list_item_1,
                //Books_genre.getGenreList()
                getDataFromDB()
        );
        listView.setAdapter(adapter);
    }

    public void onGenerAddClick(View view){
        startActivity(
                new Intent(this, AddAuthor.class)
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.author_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_Author:
                startActivity(
                        new Intent(this, AddAuthor.class)
                );
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}