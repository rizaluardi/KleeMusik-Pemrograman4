package com.appsmor.kleemusik.model;

public class Musik {
    private int id;
    private String musik;
    private String artis;

    public Musik() {
    }

    public Musik(String musik, String artis) {
        this.musik = musik;
        this.artis = artis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusik() {
        return musik;
    }

    public void setMusik(String musik) {
        this.musik = musik;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }
}