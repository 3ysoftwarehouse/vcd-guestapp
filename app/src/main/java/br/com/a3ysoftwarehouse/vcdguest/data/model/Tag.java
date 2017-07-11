package br.com.a3ysoftwarehouse.vcdguest.data.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Iago Belo on 07/07/17.
 */

public class Tag extends RealmObject implements Serializable {
    private String paxCod;
    private String tag;

    public Tag() {
    }

    public Tag(String paxCod, String tag) {
        this.paxCod = paxCod;
        this.tag = tag;
    }

    public String getPaxCod() {
        return paxCod;
    }

    public void setPaxCod(String paxCod) {
        this.paxCod = paxCod;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toJson() {
        return "{" +
                "'paxCod': '" + this.paxCod + "'," +
                "'tag': '" + this.tag + "'" +
                "}";
    }

    @Override
    public String toString() {
        return "Tag{" +
                "paxCod='" + paxCod + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
