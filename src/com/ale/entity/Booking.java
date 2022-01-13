package com.ale.entity;

public class Booking {
    private int id;
    private User user_id;
    private Jadwal jadwal_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Jadwal getJadwal_id() {
        return jadwal_id;
    }

    public void setJadwal_id(Jadwal jadwal_id) {
        this.jadwal_id = jadwal_id;
    }
}
