package br.com.a3ysoftwarehouse.vcdguest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Iago Belo on 10/07/17.
 */

public class Item extends RealmObject implements Parcelable {
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    private String id;
    private String paxCod;
    private long time;
    private double lat;
    private double lng;

    public Item() {
    }

    protected Item(Parcel in) {
        this.id = in.readString();
        this.paxCod = in.readString();
        this.time = in.readLong();
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.paxCod);
        dest.writeLong(this.time);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }
}
