package com.ale.entity;

public class Vaksin {
    private int id;
    private String nama;

    @Override
    public String toString() {
        return nama;
    }

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
}

