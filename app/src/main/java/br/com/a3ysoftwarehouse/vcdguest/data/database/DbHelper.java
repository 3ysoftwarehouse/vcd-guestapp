package br.com.a3ysoftwarehouse.vcdguest.data.database;

import android.content.Context;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.R;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
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
    public void savePassengers(List<Passenger> passengerList) {
        mRealm.beginTransaction();

        for (Passenger p : passengerList) {
            Passenger passenger = getPassenger(p.getCOD());

            if (passenger != null) {
                String tag = passenger.getTag();

                if (tag != null) p.setTag(tag);
            }

            mRealm.copyToRealmOrUpdate(p);
        }

        mRealm.commitTransaction();
    }

    @Override
    public void updatePassengerTag(final String cod, final String tag) throws DatabaseException {
        Passenger passenger = mRealm.where(Passenger.class).equalTo("tag", tag).findFirst();

        if (passenger != null) {
            throw new DatabaseException(Utils.getString(R.string.tag_already_registered));
        }

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Passenger passenger = realm.where(Passenger.class).equalTo("COD", cod).findFirst();
                passenger.setTag(tag);
            }
        });
    }

    @Override
    public void saveCall(final Call call) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                call.setId(System.currentTimeMillis());

                mRealm.copyToRealm(call);
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
}
