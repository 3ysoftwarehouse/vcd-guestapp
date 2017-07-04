package br.com.a3ysoftwarehouse.vcdguest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class NfcIdObserver {
    private static volatile NfcIdObserver instance;
    private static List<INfcTagIdListener> mListenerList;

    private NfcIdObserver() {
        mListenerList = new ArrayList<>();
    }

    public static NfcIdObserver getInstance() {
        if (instance == null) {
            synchronized (NfcIdObserver.class) {
                if (instance == null) {
                    instance = new NfcIdObserver();
                }
            }
        }

        return instance;
    }

    public void subscribe(INfcTagIdListener listener) {
        mListenerList.add(listener);
    }

    public void notifyListeners(String tag) {
        for (INfcTagIdListener listener : mListenerList) {
            listener.onNewTag(tag);
        }
    }

    public void unsubscribe(INfcTagIdListener listener) {
        mListenerList.remove(listener);
    }

    public interface INfcTagIdListener {
        void onNewTag(String tag);
    }
}
