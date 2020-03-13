package com.example.mapdemo;

import com.google.android.gms.maps.model.LatLng;

public class Pin {

    private LatLng latLng;
    private String title;
    private String id;

    public Pin(LatLng latLng, String title, String id) {
        this.latLng = latLng;
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
