package com.example.truyencuatui.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String idBook;
    private String name;
    private String status;
    private String author;
    private String teamTrans;
    private String image;
    private String descript;
    private String category;
    private String kind;
    private String linkDowload;

   public Book(){

   }

    public String getLinkDowload() {
        return linkDowload;
    }

    public void setLinkDowload(String linkDowload) {
        this.linkDowload = linkDowload;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTeamTrans() {
        return teamTrans;
    }

    public void setTeamTrans(String teamTrans) {
        this.teamTrans = teamTrans;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
