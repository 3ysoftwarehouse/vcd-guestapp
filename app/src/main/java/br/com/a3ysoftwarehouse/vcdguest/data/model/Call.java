package br.com.a3ysoftwarehouse.vcdguest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class Call extends RealmObject implements Parcelable {
    public static final Parcelable.Creator<Call> CREATOR = new Parcelable.Creator<Call>() {
        @Override
        public Call createFromParcel(Parcel source) {
            return new Call(source);
        }

        @Override
        public Call[] newArray(int size) {
            return new Call[size];
        }
    };
    private long id;
    private long initTime;
    private long endTime;
    private RealmList<Item> items;

    public Call() {
    }

    protected Call(Parcel in) {
        this.id = in.readLong();
        this.initTime = in.readLong();
        this.endTime = in.readLong();
        this.items = new RealmList<>();
        in.readList(this.items, Item.class.getClassLoader());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInitTime() {
        return initTime;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        if (items == null) {
            setItems(new RealmList<Item>());
            getItems().add(item);

        } else {
            getItems().add(item);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.initTime);
        dest.writeLong(this.endTime);
        dest.writeList(this.items);
    }
}
