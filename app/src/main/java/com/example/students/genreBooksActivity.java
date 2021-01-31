package com.example.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.students.BooksListActivity.AUTHOR;

public class genreBooksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_books2);

        Intent intent = getIntent();
        int author = intent.getIntExtra(AUTHOR, 0);

        Books_genre books_genre = null;
        SQLiteOpenHelper sqLiteHelper = new BooksDatabaseHelper(this);
        try{
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
            Cursor cursor = db.query("Authors", new String[] {"author", "genre", "countryOfIssue", "criticallyTestedFlg", "onSaleFlg", "id"}, "id=?", new String[]{Integer.toString(author)}, null, null, null);
            if(cursor.moveToFirst()){
                books_genre = new Books_genre(
                        cursor.getInt(5),
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        (cursor.getInt(3) > 0),
                        (cursor.getInt(4) > 0)
                );
            }else{
                Toast toast = Toast.makeText(this,
                        "Can`t find author with id" + Integer.toString(Integer.parseInt(Integer.toString(author))),Toast.LENGTH_SHORT);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,
                    "Database unavailabale", Toast.LENGTH_SHORT);
            toast.show();
        }

        EditText txtAuthor = (EditText) findViewById(R.id.editTextAuthor);
        txtAuthor.setText(books_genre.getAuthor());

        EditText txtGenre = (EditText) findViewById(R.id.editTextGenre);
        txtGenre.setText(books_genre.getGenre());

        TextView txtImageAuthor = (TextView) findViewById(R.id.AuthorText2);
        txtImageAuthor.setText(books_genre.getAuthor());

        TextView txtImageGenre = (TextView) findViewById(R.id.GenreText2);
        txtImageGenre.setText(books_genre.getGenre());

        if(books_genre.getCountryOfIssue() == 0){
            ((RadioButton) findViewById(R.id.radio_countryOfIssue_Usa2)).setChecked(true);
        }
        else{
            ((RadioButton) findViewById(R.id.radio_UnitedKingdom2)).setChecked(true);
        }

        ((CheckBox) findViewById(R.id.CBcriticallyTestedFlg2)).setChecked(books_genre.isCriticallyTestedFlg());
        ((CheckBox) findViewById(R.id.CBonSaleFlg2)).setChecked(books_genre.isOnSaleFlg());

    }
    public void onBtnOkClick(View view) {
        String outString = "Автор " + ((TextView) findViewById(R.id.editTextAuthor)).getText() + "\n";
        outString +=  "Жанр " + ((TextView) findViewById(R.id.editTextGenre)).getText() + "\n" + "Cтрана издания ";
        if(((RadioButton) findViewById(R.id.radio_countryOfIssue_Usa2)).isChecked()){
            outString +=  ((TextView) findViewById(R.id.radio_countryOfIssue_Usa2)).getText() + "\n";
        }else{
            outString +=  ((TextView) findViewById(R.id.radio_UnitedKingdom2)).getText() + "\n";
        }
        if(((CheckBox)findViewById(R.id.CBcriticallyTestedFlg2)).isChecked()){
            outString += "было проверено критиком\n";
        }else{
            outString += "не было проверено критиком\n";
        }
        outString += "В данный момент ";
        if(((CheckBox)findViewById(R.id.CBonSaleFlg2)).isChecked()){
            outString += "В продаже\n";
        }else{
            outString += "изнят из продажи\n";
        }
        Toast.makeText(this, outString, Toast.LENGTH_LONG).show();
    }
    public void onBtnBooksListClick(View view) {
        Intent localIntent = getIntent();
        String Book =  localIntent.getStringExtra(AUTHOR);
         Intent newintent = new Intent(this, BooksListActivity.class);
         newintent.putExtra(AUTHOR, Book);
         startActivity(newintent);
    }

}