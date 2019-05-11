package com.cafe_papsi.aplikasipapsicafe.model;

public class Menu {
    private String id;
    private String namaMenu;
    private String hargaMenu;
    private String linkGambar;

    public Menu (String id, String namaMenu, String hargaMenu, String linkGambar){
        this.id = id;
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
        this.linkGambar = linkGambar;
    }

    public String getId () { return id;}

    public void setId (String id) { this.id = id; }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(String hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public String getLinkGambar() {
        return linkGambar;
    }

    public void setLinkGambar(String linkGambar) {
        this.linkGambar = linkGambar;
    }
}
