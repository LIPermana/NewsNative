package com.example.news;

public class Item {
    private String gambar, title, deskripsi, url, konten;
    public Item (String mgambar, String mtitle, String mdeskripsi, String murl, String mkonten){
        gambar = mgambar;
        title = mtitle;
        deskripsi = mdeskripsi;
        url = murl;
        konten = mkonten;
    }

    public String getGambar() {
        return gambar;
    }

    public String getTitle() {
        return title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getUrl() {
        return url;
    }

    public String getKonten() {
        return konten;
    }
}
