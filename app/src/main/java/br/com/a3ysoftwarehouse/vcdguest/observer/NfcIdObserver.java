package br.com.a3ysoftwarehouse.vcdguest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class NfcIdObserver {
    private static volatile NfcIdObserver instance;
    private static List<INfcTagIdListener> listenerList;

    private NfcIdObserver() {
        listenerList = new ArrayList<>();
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
        listenerList.add(listener);
    }

    public void notifyListeners(String tag) {
        for (INfcTagIdListener listener : listenerList) {
            listener.onNewTag(tag);
        }
    }

    public void unsubscribe(INfcTagIdListener listener) {
        listenerList.remove(listener);
    }

    public interface INfcTagIdListener {
        void onNewTag(String tag);
    }
}
