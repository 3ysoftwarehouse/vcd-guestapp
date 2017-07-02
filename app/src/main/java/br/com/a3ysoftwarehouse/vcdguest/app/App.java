package br.com.a3ysoftwarehouse.vcdguest.app;

import android.app.Application;
import android.content.Context;

import com.androidnetworking.AndroidNetworking;

import br.com.a3ysoftwarehouse.vcdguest.data.DataManager;
import br.com.a3ysoftwarehouse.vcdguest.data.IDataManager;

/**
 * Created by Iago Belo on 15/06/17.
 */

public class App extends Application {
    // Instance
    private static App instance;

    private IDataManager mIDataManager;

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Sync Passengers
        mIDataManager = DataManager.getInstance();
        mIDataManager.syncPassengers();

        // Android Networking Init
        AndroidNetworking.initialize(this);
    }
}
