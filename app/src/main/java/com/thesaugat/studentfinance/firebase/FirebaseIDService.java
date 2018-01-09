package com.thesaugat.studentfinance.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.thesaugat.studentfinance.helper.Constants;
import com.thesaugat.studentfinance.utils.SharedPreferencesUtils;

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sharedPreferencesUtils = new SharedPreferencesUtils();
        sharedPreferencesUtils.setStringPreference(this, Constants.RefreshedTokenFireBase, refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }


    private void sendRegistrationToServer(String token) {

        System.out.println("66666666666 " + token);
        // Add custom implementation, as needed.
    }
}