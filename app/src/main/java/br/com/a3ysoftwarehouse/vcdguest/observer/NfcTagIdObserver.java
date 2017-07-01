package br.com.a3ysoftwarehouse.vcdguest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iago Belo on 23/06/2017.
 */

public class NfcTagIdObserver {
    private static volatile NfcTagIdObserver instance;
    private List<INfcTagIdListener> listenerList;

    public interface INfcTagIdListener {
        void onNewTag(String tag);
    }

    private NfcTagIdObserver() {
        listenerList = new ArrayList<>();
    }

    public static NfcTagIdObserver getInstance() {
        if (instance == null) {
            synchronized (NfcTagIdObserver.class) {
                if (instance == null) {
                    instance = new NfcTagIdObserver();
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
}
