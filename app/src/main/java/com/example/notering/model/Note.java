package com.example.notering.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

       @PrimaryKey(autoGenerate = true)
        private int id;

       @ColumnInfo(name = "title")
       private String title;

       @ColumnInfo(name = "content")
       private String content;

       public Note(String title, String content){
          this.title = title;
          this.content = content;
       }

       //Getters

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
