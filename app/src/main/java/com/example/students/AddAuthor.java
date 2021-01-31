package com.example.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddAuthor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);
    }
    public void onGenerAddClick(View view){
        EditText author = (EditText) findViewById(R.id.addAuthor);
        EditText genre = (EditText) findViewById(R.id.addGenre);
        Books_genre.addGenre(
                new Books_genre(author.getText().toString(),
                        genre.getText().toString(),
                        0,false, false)
        );
        NavUtils.navigateUpFromSameTask(this);
    }
}