package br.com.a3ysoftwarehouse.vcdguest.data;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.database.DbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.database.IDbHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Call;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Tag;
import br.com.a3ysoftwarehouse.vcdguest.data.network.ApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.network.IApiHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.preferences.IPreferencesHelper;
import br.com.a3ysoftwarehouse.vcdguest.data.preferences.PreferencesHelper;
import br.com.a3ysoftwarehouse.vcdguest.exception.DatabaseException;
import br.com.a3ysoftwarehouse.vcdguest.observer.BackupTagObservable;
import br.com.a3ysoftwarehouse.vcdguest.observer.RestoreTagObservable;

/**
 * Created by Iago Belo on 21/06/17.
 */

public class DataManager implements IDataManager {
    // Constants
    private static final String TAG = "DataManager";
    private static final String USER_PREFS_NAME = "user_prefs";

    // Instance
    private static volatile IDataManager instance;

    // PreferencesHelper
    private final IPreferencesHelper mIPreferencesHelper;

    // ApiHelper
    private final IApiHelper mIApiHelper;

    // DbHelper
    private final IDbHelper mIDbHelper;

    // Observables
    private BackupTagObservable mBackupTagObservable;
    private RestoreTagObservable mRestoreTagObservable;

    private DataManager() {
        mIApiHelper = new ApiHelper(App.getContext());
        mIDbHelper = new DbHelper(App.getContext());
        mIPreferencesHelper = new PreferencesHelper(App.getContext(), USER_PREFS_NAME);

        AndroidNetworking.initialize(App.getContext());

        mBackupTagObservable = BackupTagObservable.getInstance();
        mRestoreTagObservable = RestoreTagObservable.getInstance();
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

    @Override
    public void syncPassengers(final ISyncListener listener) {
        mIApiHelper.getPassengers(new IApiHelper.IApiRequestListener<List<Passenger>>() {
            @Override
            public void onSuccess(List<Passenger> passengerList) {
                mIDbHelper.savePassengers(passengerList);

                listener.onSuccess();
            }

            @Override
            public void onFailed() {
                listener.onFailed();
            }
        });
    }

    @Override
    public void restoreTagsFromServer(final ISyncListener listener) {
        mIApiHelper.restoreTags(new IApiRequestListener<List<Tag>>() {
            @Override
            public void onSuccess(List<Tag> tags) {
                mIDbHelper.saveTags(tags);

                mRestoreTagObservable.notifyListenersOnSuccess();
            }

            @Override
            public void onFailed() {
                mRestoreTagObservable.notifyListenersOnFailed();
            }
        });
    }

    @Override
    public void backupTagsToServer(final ISyncListener listener) {
        mIApiHelper.backupTags(mIDbHelper.getAllTags(), new IApiRequestListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mBackupTagObservable.notifyListenersOnSuccess();
            }

            @Override
            public void onFailed() {
                mBackupTagObservable.notifyListenersOnFailed();
            }
        });
    }

    @Override
    public List<Passenger> getPassenger() {
        return mIDbHelper.getPassenger();
    }

    @Override
    public Passenger getPassenger(String cod) {
        return mIDbHelper.getPassenger(cod);
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
    public void newPassengerTag(String cod, String tag) throws DatabaseException {
        mIDbHelper.newPassengerTag(cod, tag);
    }

    @Override
    public List<Tag> getPassengerTags(String cod) {
        return mIDbHelper.getPassengerTags(cod);
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

    @Override
    public List<Tag> getAllTags() {
        return mIDbHelper.getAllTags();
    }

    @Override
    public void saveTags(List<Tag> tagList) {
        mIDbHelper.saveTags(tagList);
    }

    @Override
    public void getPassengers(IApiRequestListener<List<Passenger>> listener) {
        mIApiHelper.getPassengers(listener);
    }

    @Override
    public void restoreTags(IApiRequestListener<List<Tag>> listener) {
        mIApiHelper.restoreTags(listener);
    }

    @Override
    public void backupTags(List<Tag> tagList, IApiRequestListener<Void> listener) {
        mIApiHelper.backupTags(tagList, listener);
    }

    @Override
    public void downloadFile(String url, String dirPath, String fileName,
                             IApiRequestListener<Void> listener) {
        mIApiHelper.downloadFile(url, dirPath, fileName, listener);
    }

    @Override
    public boolean getIsLogged() {
        return mIPreferencesHelper.getIsLogged();
    }

    @Override
    public void setIsLogged(boolean isLogged) {
        mIPreferencesHelper.setIsLogged(isLogged);
    }
}
