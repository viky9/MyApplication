package com.example.prashant.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by prashant on 31-08-2017.
 */

public class ContCreateTokenService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent serviceIntent = new Intent(this, MyFirebaseMessagingService.class);
        startService(serviceIntent);
        return START_REDELIVER_INTENT;
    }
}