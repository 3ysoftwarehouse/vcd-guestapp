package br.com.a3ysoftwarehouse.vcdguest.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class PassengerCall extends RealmObject {
    private int id;
    private RealmList<PassengerCheck> passengerChecks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<PassengerCheck> getPassengerChecks() {
        return passengerChecks;
    }

    public void setPassengerChecks(RealmList<PassengerCheck> passengerChecks) {
        this.passengerChecks = passengerChecks;
    }
}
