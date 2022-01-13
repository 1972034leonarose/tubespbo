package com.ale.entity;

public class TempatVaksin {
    private int id;
    private String nama;
    private String alamat;
    private String noTlpn;
    private String email;
    private String kota;

    public TempatVaksin(){
    }

    @Override
    public String toString() {
        return nama;
    }

    public Vaksin getVaksin_id() {
        return vaksin_id;
    }

    public void setVaksin_id(Vaksin vaksin_id) {
        this.vaksin_id = vaksin_id;
    }

    private Vaksin vaksin_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTlpn() {
        return noTlpn;
    }

    public void setNoTlpn(String noTlpn) {
        this.noTlpn = noTlpn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}

