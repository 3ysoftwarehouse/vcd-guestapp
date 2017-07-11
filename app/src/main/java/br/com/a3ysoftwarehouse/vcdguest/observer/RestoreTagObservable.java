package br.com.a3ysoftwarehouse.vcdguest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 11/07/17.
 */

public class RestoreTagObservable {
    // Constants
    private static final String TAG = "RestoreTagObservable";

    private static volatile RestoreTagObservable instance;
    private static volatile List<IRestoreTagObserver> mObserversList;

    private RestoreTagObservable() {
        mObserversList = new ArrayList<>();
    }

    public static RestoreTagObservable getInstance() {
        if (instance == null) {
            synchronized (RestoreTagObservable.class) {
                if (instance == null) {
                    instance = new RestoreTagObservable();
                }
            }
        }

        return instance;
    }

    public void subscribe(IRestoreTagObserver listener) {
        mObserversList.add(listener);
    }

    public void notifyListenersOnSuccess() {
        for (IRestoreTagObserver listener : mObserversList) {
            listener.onRestoreTagSuccess();
        }
    }

    public void notifyListenersOnFailed() {
        for (IRestoreTagObserver listener : mObserversList) {
            listener.onRestoreTagFailed();
        }
    }

    public synchronized void unsubscribe(IRestoreTagObserver listener) {
        mObserversList.remove(listener);
    }

    public interface IRestoreTagObserver {
        void onRestoreTagSuccess();

        void onRestoreTagFailed();
    }
}
