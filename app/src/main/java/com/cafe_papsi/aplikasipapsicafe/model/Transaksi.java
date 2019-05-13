package com.cafe_papsi.aplikasipapsicafe.model;

public class Transaksi {
    private String id;
    private String idUser;
    private String noMeja;
    private String total;
    private String diskon;
    private String tglTransaksi;

    public Transaksi(String id, String idUser, String noMeja, String total, String diskon, String tglTransaksi) {
        this.id = id;
        this.idUser = idUser;
        this.noMeja = noMeja;
        this.total = total;
        this.diskon = diskon;
        this.tglTransaksi = tglTransaksi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setNoMeja(String noMeja) {
        this.noMeja = noMeja;
    }

    public String getNoMeja() {
        return noMeja;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }
}
