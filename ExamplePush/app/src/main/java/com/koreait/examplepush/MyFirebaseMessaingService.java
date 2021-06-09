package com.koreait.examplepush;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MyFirebaseMessaingService extends FirebasemessaingService {
    private static final String TAG = "FMS";

    public MyFirebaseMessaingService() {


    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}