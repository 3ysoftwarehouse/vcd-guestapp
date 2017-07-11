package br.com.a3ysoftwarehouse.vcdguest.data.model;

import io.realm.RealmObject;

/**
 * Created by Iago Belo on 10/07/17.
 */

public class Item extends RealmObject {
    private String id;
    private String paxCod;
    private long time;
    private double lat;
    private double lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaxCod() {
        return paxCod;
    }

    public void setPaxCod(String paxCod) {
        this.paxCod = paxCod;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
