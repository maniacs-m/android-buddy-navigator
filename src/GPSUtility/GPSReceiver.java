package jemboy.navitwo.GPSUtility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import jemboy.navitwo.Network.UploadCoordinatesTask;

public class GPSReceiver extends BroadcastReceiver {
    private float latitude, longitude, degree;
    private boolean isLocked = false;

    public GPSReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isLocked) {
            isLocked = true;
            Bundle bundle = intent.getExtras();
            if (bundle.getString("action").equals("GPSInfo")) {
                latitude = bundle.getFloat("latitude");
                longitude = bundle.getFloat("longitude");
                Log.d("Tag: ", latitude + " " + longitude);
            }
            else if (bundle.getString("action").equals("CompassInfo")) {
                degree = bundle.getFloat("degree");
            }
            // Rotate shite
            isLocked = false;
        }
    }
}
