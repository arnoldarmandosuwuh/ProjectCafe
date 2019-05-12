package com.cafe_papsi.aplikasipapsicafe.model;

public class User {
    private String id;
    private String namaUser;
    private String urlGambar;

    public User (String id, String namaUser, String urlGambar){
        this.id = id;
        this.namaUser = namaUser;
        this.urlGambar = urlGambar;
    }
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getUrlGambar() {
        return urlGambar;
    }

    public void setUrlGambar(String urlGambar) {
        this.urlGambar = urlGambar;
    }

}
