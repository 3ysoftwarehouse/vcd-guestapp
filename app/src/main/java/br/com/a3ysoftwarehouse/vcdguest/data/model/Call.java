package br.com.a3ysoftwarehouse.vcdguest.data.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Iago Belo on 22/06/17.
 */

public class Call extends RealmObject {
    private long id;
    private RealmList<Passenger> passengersList;

    public Call() {
        passengersList = new RealmList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Passenger> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(RealmList<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public void addPassenger(Passenger passenger) {
        getPassengersList().add(passenger);
    }
}
