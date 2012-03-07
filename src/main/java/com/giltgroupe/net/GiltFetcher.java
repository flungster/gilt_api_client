package com.giltgroupe.net;

import com.giltgroupe.model.Gilt;

public class GiltFetcher {
    private static Gilt _gilt = null;

    protected GiltFetcher() {

    }

    public synchronized static Gilt getInstance() {
        if (_gilt == null) {
            _gilt = new Gilt();
        }
        return _gilt;
    }

}