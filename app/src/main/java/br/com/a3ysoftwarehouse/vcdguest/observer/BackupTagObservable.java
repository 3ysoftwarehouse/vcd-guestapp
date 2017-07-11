package br.com.a3ysoftwarehouse.vcdguest.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 11/07/17.
 */

public class BackupTagObservable {
    // Constants
    private static final String TAG = "BackupTagObservable";

    private static volatile BackupTagObservable instance;
    private static volatile List<IBackupTagObserver> mObserverList;

    private BackupTagObservable() {
        mObserverList = new ArrayList<>();
    }

    public static BackupTagObservable getInstance() {
        if (instance == null) {
            synchronized (BackupTagObservable.class) {
                if (instance == null) {
                    instance = new BackupTagObservable();
                }
            }
        }

        return instance;
    }

    public void subscribe(IBackupTagObserver listener) {
        mObserverList.add(listener);
    }

    public void notifyListenersOnSuccess() {
        for (IBackupTagObserver listener : mObserverList) {
            listener.onBackupTagSuccess();
        }
    }

    public void notifyListenersOnFailed() {
        for (IBackupTagObserver listener : mObserverList) {
            listener.onBackupTagFailed();
        }
    }

    public synchronized void unsubscribe(IBackupTagObserver listener) {
        mObserverList.remove(listener);
    }

    public interface IBackupTagObserver {
        void onBackupTagSuccess();

        void onBackupTagFailed();
    }
}
