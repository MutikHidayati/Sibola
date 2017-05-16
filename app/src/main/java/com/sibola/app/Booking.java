package com.sibola.app;

import java.util.Date;

/**
 * Created by Aizen on 10 Mei 2017.
 */

public class Booking {
    private String tanggal;
    private String slotJam;
    private String username;
    private boolean lunas;

    public Booking(String tanggal, String slotJam) {
        this.tanggal = tanggal;
        this.slotJam = slotJam;
        this.lunas = false;
    }

    public Booking(String tanggal, String slotJam, String username) {
        this.tanggal = tanggal;
        this.slotJam = slotJam;
        this.username = username;
        this.lunas = false;
    }

    public Booking() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSlotJam() {
        return slotJam;
    }

    public void setSlotJam(String slotJam) {
        this.slotJam = slotJam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLunas() {
        return lunas;
    }

    public void setLunas(boolean lunas) {
        this.lunas = lunas;
    }
}
