package io.github.paveltishkin.lightdistance;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;

/**
 * Created by Pavel Tishkin on 11/04/16.
 */
public class LightRangingListener implements BeaconManager.RangingListener {

    LightDistanceActivity lightDistanceActivity;
    int beacon1TextViewId;
    int beacon2TextViewId;
    int beacon3TextViewId;
    int beacon1Major;
    int beacon2Major;
    int beacon3Major;

    public LightRangingListener(LightDistanceActivity lightDistanceActivity, int beacon1TextViewId, int beacon2TextViewId, int beacon3TextViewId,
                                int beacon1Major, int beacon2Major, int beacon3Major) {
        this.lightDistanceActivity = lightDistanceActivity;
        this.beacon1TextViewId = beacon1TextViewId;
        this.beacon2TextViewId = beacon2TextViewId;
        this.beacon3TextViewId = beacon3TextViewId;
        this.beacon1Major = beacon1Major;
        this.beacon2Major = beacon2Major;
        this.beacon3Major = beacon3Major;
    }

    @Override
    public void onBeaconsDiscovered(Region region, List<Beacon> beaconList) {
        System.out.print("Beacons: " + beaconList);
        lightDistanceActivity.updateTextView(beacon1TextViewId, 0);
        lightDistanceActivity.updateTextView(beacon2TextViewId, 0);
        lightDistanceActivity.updateTextView(beacon3TextViewId, 0);
        int redIntensity = 50;
        int greenIntensity = 50;
        int blueIntensity = 50;

        for (Beacon currentBeacon : beaconList) {
            int rssi = currentBeacon.getRssi();
            int txPower = currentBeacon.getMeasuredPower();
            int colorIntensity = calculateColorIntensity(txPower, rssi);

            if (currentBeacon.getMajor() == beacon1Major) {
                lightDistanceActivity.updateTextView(beacon1TextViewId, rssi);
                redIntensity = colorIntensity;
            }
            else if (currentBeacon.getMajor() == beacon2Major) {
                lightDistanceActivity.updateTextView(beacon2TextViewId, rssi);
                greenIntensity = colorIntensity;
            }
            else if (currentBeacon.getMajor() == beacon3Major) {
                lightDistanceActivity.updateTextView(beacon3TextViewId, rssi);
                blueIntensity = colorIntensity;
            }
        }
        lightDistanceActivity.updateBackgroundColor(redIntensity, greenIntensity, blueIntensity);
    }

    private static int calculateColorIntensity(int txPower, double rssi) {
        if (rssi == 0) {
            return 50;
        }

        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            return Math.max(255 - Math.min((int) (Math.pow(ratio, 10) * 160), 255), 50);
        }
        else {
            double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;
            return Math.max(255 - Math.min((int) (accuracy * 160), 255), 50);
        }
    }
}
