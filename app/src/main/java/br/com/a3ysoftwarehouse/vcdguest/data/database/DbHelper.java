package br.com.a3ysoftwarehouse.vcdguest.data.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
import br.com.a3ysoftwarehouse.vcdguest.exception.DatabaseException;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;
import io.realm.Realm;

/**
 * Created by Iago Belo on 22/06/2017.
 */

public class DbHelper implements IDbHelper {
    // Constants
    private static final String TAG = "DbHelper";

    private Realm mRealm;

    public DbHelper(Context context) {
        Realm.init(context);

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public List<Passenger> getPassenger() {
        return mRealm.where(Passenger.class).findAll();
    }

    @Override
    public Passenger getPassenger(String cod) {
        return mRealm.where(Passenger.class).equalTo("COD", cod).findFirst();
    }

    @Override
    public Passenger getPassengerByTag(String tag) {
        List<Passenger> passengerList = getPassenger();

        for (Passenger p : passengerList) {
            for (Tag t : p.getTagList()) {
                if (t.getTag().equals(tag)) return p;
            }
        }

        return null;
    }

    @Override
    public void savePassengers(final List<Passenger> passengerList) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Passenger newP : passengerList) {
                    Passenger oldP = getPassenger(newP.getCOD());

                    if (oldP != null) {
                        //oldP.setCOD(oldP.getCOD());
                        oldP.setAGENCIA(oldP.getAGENCIA());
                        oldP.setCELPAX(oldP.getCELPAX());
                        oldP.setCIA(oldP.getCIA());
                        oldP.setCODR1(oldP.getCODR1());
                        oldP.setCODR2(oldP.getCODR2());
                        oldP.setCODR3(oldP.getCODR3());
                        oldP.setDATA(oldP.getDATA());
                        oldP.setDATANASCIMENTO(oldP.getDATANASCIMENTO());
                        oldP.setEMAILPAX(oldP.getEMAILPAX());
                        oldP.setEMISSOR(oldP.getEMISSOR());
                        oldP.setESCOLA(oldP.getESCOLA());
                        oldP.setETKT(oldP.getETKT());
                        oldP.setFONERESP(oldP.getFONERESP());
                        oldP.setGRUPO(oldP.getGRUPO());
                        oldP.setLOC(oldP.getLOC());
                        oldP.setOPCIONAIS(oldP.getOPCIONAIS());
                        oldP.setOPCIONAL1(oldP.getOPCIONAL1());
                        oldP.setOPCIONAL2(oldP.getOPCIONAL2());
                        oldP.setOPCIONAL3(oldP.getOPCIONAL3());
                        oldP.setOPCIONAL4(oldP.getOPCIONAL4());
                        oldP.setOPCIONAL5(oldP.getOPCIONAL5());
                        oldP.setOPCIONAL6(oldP.getOPCIONAL6());
                        oldP.setOPCIONAL7(oldP.getOPCIONAL7());
                        oldP.setQUARTO(oldP.getQUARTO());
                        oldP.setREFEICOES(oldP.getREFEICOES());
                        oldP.setROMMATE1(oldP.getROMMATE1());
                        oldP.setROMMATE2(oldP.getROMMATE2());
                        oldP.setROMMATE3(oldP.getROMMATE3());
                        oldP.setROMMATES(oldP.getROMMATES());
                        oldP.setSEXO(oldP.getSEXO());
                        oldP.setVOO(oldP.getVOO());
                        oldP.setPAX(oldP.getPAX());
                        oldP.setEMAILRESP(oldP.getEMAILRESP());

                    } else {
                        realm.copyToRealmOrUpdate(newP);
                    }
                }
            }
        });
    }

    @Override
    public void newPassengerTag(final String cod, final String tag) throws DatabaseException {
        final Tag t = mRealm.where(Tag.class).equalTo("tag", tag).findFirst();

        if (t != null) {
            throw new DatabaseException(Utils.getString(R.string.tag_already_registered));
        }

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Passenger passenger = getPassenger(cod);

                if (passenger != null) {
                    Log.i(TAG, "passenger != null");

                    Tag t = realm.createObject(Tag.class);
                    t.setTag(tag);
                    t.setPaxCod(passenger.getCOD());

                    passenger.getTagList().add(t);

                }
            }
        });
    }

    @Override
    public List<Tag> getPassengerTags(String cod) {
        Passenger p = mRealm.where(Passenger.class).equalTo("COD", cod).findFirst();

        return p.getTagList();
    }

    @Override
    public void saveCall(final Call call) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                call.setId(System.currentTimeMillis());

                realm.copyToRealm(call);
            }
        });
    }

    @Override
    public List<Call> getCall() {
        return mRealm.where(Call.class).findAll();
    }

    @Override
    public Call getCall(long id) {
        return mRealm.where(Call.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<Tag> getAllTags() {
        return mRealm.where(Tag.class).findAll();
    }

    @Override
    public void saveTags(final List<Tag> tagList) {
        for (Tag t : tagList) {
            try {
                newPassengerTag(t.getPaxCod(), t.getTag());
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
        }
    }
}
