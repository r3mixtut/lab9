package com.example.students;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Books_genre {
    private int id;
    private String author;
    private String genre;
    private int countryOfIssue;
    private boolean criticallyTestedFlg;
    private boolean onSaleFlg;

    public Books_genre( String author, String genre, int countryOfIssue, boolean criticallyTestedFlg, boolean onSaleFlg){
        this.author = author;
        this.genre = genre;
        this.countryOfIssue = countryOfIssue;
        this.criticallyTestedFlg = criticallyTestedFlg;
        this.onSaleFlg = onSaleFlg;
    }
    public Books_genre(int id, String author, String genre, int countryOfIssue, boolean criticallyTestedFlg, boolean onSaleFlg){
        this(author, genre,countryOfIssue,criticallyTestedFlg,onSaleFlg);
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public int getCountryOfIssue(){
        return countryOfIssue;
    }

    public boolean isCriticallyTestedFlg(){
        return criticallyTestedFlg;
    }

    public boolean isOnSaleFlg(){
        return onSaleFlg;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCriticallyTestedFlg(boolean criticallyTestedFlg) {
        this.criticallyTestedFlg = criticallyTestedFlg;
    }

    public void setOnSaleFlg(boolean onSaleFlg) {
        this.onSaleFlg = onSaleFlg;
    }

    public void setCountryOfIssue(int countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }
    public static  void addGenre(Books_genre genre){genreList.add(genre);}

    private  static ArrayList<Books_genre> genreList = new ArrayList<Books_genre>(Arrays.asList(
            new Books_genre("Джоан Роулинг", "приключения, юмор, фэнтези",1,true, true),
            new Books_genre("Стивен Кинг", "ужасы",0, true, true),
            new Books_genre("Генри Форд", "бизнес книга", 0, true, true)
    ));

    public static Books_genre getGenreList(String genre){
        for(Books_genre b: genreList){
            if(b.getAuthor().equals(genre)){
                return b;
            }
        }
        return null;
    }
    public static ArrayList<Books_genre>getGenreList(){
        return genreList;
    }

    @Override
    public String toString() {
        return author;
    }
}
