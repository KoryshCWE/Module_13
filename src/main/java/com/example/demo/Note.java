package com.example.demo;

public class Note {
    private static int idCounter = 0;
    private int id;
    private String title;
    private String content;


    public Note() {
        this.id = ++idCounter;
    }


    public Note(String title, String content) {
        this.id = ++idCounter;
        this.title = title;
        this.content = content;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setId(long id) {
    }
}
