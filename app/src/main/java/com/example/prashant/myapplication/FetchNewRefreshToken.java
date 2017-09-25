package com.example.prashant.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by prashant on 31-08-2017.
 */


public class FetchNewRefreshToken extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String TAG = FetchNewRefreshToken.class.getSimpleName();


    public FetchNewRefreshToken() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {

            // Resets Instance ID and revokes all tokens.
            FirebaseInstanceId.getInstance().deleteInstanceId();


            // Now manually call onTokenRefresh()
            Log.e(TAG, "Getting new token");
            FirebaseInstanceId.getInstance().getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}