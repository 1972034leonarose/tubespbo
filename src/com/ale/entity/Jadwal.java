package com.ale.entity;


public class Jadwal {
    private int id;
    private String jam;
    private String tanggal;
    private TempatVaksin tempatVaksin;

    @Override
    public String toString() {
        return jam+tanggal+tempatVaksin;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public TempatVaksin getTempatVaksin() {
        return tempatVaksin;
    }

    public void setTempatVaksin(TempatVaksin tempatVaksin) {
        this.tempatVaksin = tempatVaksin;
    }
}
