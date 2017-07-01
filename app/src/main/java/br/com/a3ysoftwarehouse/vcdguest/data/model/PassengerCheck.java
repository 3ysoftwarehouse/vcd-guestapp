package br.com.a3ysoftwarehouse.vcdguest.data.model;

import io.realm.RealmObject;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class PassengerCheck extends RealmObject {
    private int id;
    private Passenger passenger;
    private boolean isPresent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
