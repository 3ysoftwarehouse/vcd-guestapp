package br.com.a3ysoftwarehouse.vcdguest.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.database.DbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.network.ApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.network.IApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.exception.DatabaseException;

/**
 * Created by Iago Belo on 21/06/17.
 */

public class DataManager implements IDataManager {
    // Constants
    public static final long CALL_CACHE_ID = 98989898;
    private static final String TAG = "DataManager";

    // Instance
    private static volatile IDataManager instance;

    // ApiHelper
    private IApiHelper mIApiHelper;

    // DbHelper
    private IDbHelper mIDbHelper;

    // List Observer
    private List<ISyncListener> mISyncListeners;

    private DataManager() {
        mIApiHelper = new ApiHelper(App.getContext());
        mIDbHelper = new DbHelper(App.getContext());
        mISyncListeners = new ArrayList<>();
    }

    public static IDataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager();
                }
            }
        }

        return instance;
    }

    private void notifyListenersOnSuccess() {
        for (ISyncListener i : mISyncListeners) {
            i.onSuccess();
        }
    }

    private void notifyListenersOnFailed() {
        for (ISyncListener i : mISyncListeners) {
            i.onFailed();
        }
    }

    @Override
    public void syncPassengers() {
        mIApiHelper.getPassengers(new IApiHelper.IApiRequestListener<List<Passenger>>() {
            @Override
            public void onSuccess(List<Passenger> passengerList) {
                mIDbHelper.savePassengers(passengerList);

                notifyListenersOnSuccess();
            }

            @Override
            public void onFailed() {
                Log.e(TAG, "Erro ao sincronizar passageiros.");

                notifyListenersOnFailed();
            }
        });
    }

    @Override
    public void subscribePassengerSync(ISyncListener listener) {
        mISyncListeners.add(listener);
    }

    @Override
    public void saveCallCache(Call call) {
        call.setId(CALL_CACHE_ID);
        mIDbHelper.saveCall(call);
    }

    @Override
    public Call getCallCache() {
        return mIDbHelper.getCall(CALL_CACHE_ID);
    }

    @Override
    public List<Passenger> getPassengerByCod() {
        return mIDbHelper.getPassengerByCod();
    }

    @Override
    public Passenger getPassengerByCod(String cod) {
        return mIDbHelper.getPassengerByCod(cod);
    }

    @Override
    public Passenger getPassengerByTag(String tag) {
        return mIDbHelper.getPassengerByTag(tag);
    }

    @Override
    public void savePassengers(List<Passenger> passengerList) {
        mIDbHelper.savePassengers(passengerList);
    }

    @Override
    public void updatePassengerTag(String cod, String tag) throws DatabaseException {
        mIDbHelper.updatePassengerTag(cod, tag);
    }

    @Override
    public void saveCall(Call call) {
        mIDbHelper.saveCall(call);
    }

    @Override
    public List<Call> getCall() {
        return mIDbHelper.getCall();
    }

    @Override
    public Call getCall(long id) {
        return mIDbHelper.getCall(id);
    }
}
