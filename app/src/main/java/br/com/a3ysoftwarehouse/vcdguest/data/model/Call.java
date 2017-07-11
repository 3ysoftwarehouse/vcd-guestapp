package br.com.a3ysoftwarehouse.vcdguest.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class Call extends RealmObject {
    private long id;
    private RealmList<Item> items;

    public Call() {
        items = new RealmList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        getItems().add(item);
    }
}
