package br.com.a3ysoftwarehouse.vcdguest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class NfcIdObservable {
    private static volatile NfcIdObservable instance;
    private static volatile List<INfcTagIdObserver> mListenerList;

    private NfcIdObservable() {
        mListenerList = new ArrayList<>();
    }

    public static NfcIdObservable getInstance() {
        if (instance == null) {
            synchronized (NfcIdObservable.class) {
                if (instance == null) {
                    instance = new NfcIdObservable();
                }
            }
        }

        return instance;
    }

    public void subscribe(INfcTagIdObserver listener) {
        mListenerList.add(listener);
    }

    public void notifyListeners(String tag) {
        for (INfcTagIdObserver listener : mListenerList) {
            listener.onNewTag(tag);
        }
    }

    public synchronized void unsubscribe(INfcTagIdObserver listener) {
        mListenerList.remove(listener);
    }

    public interface INfcTagIdObserver {
        void onNewTag(String tag);
    }
}
