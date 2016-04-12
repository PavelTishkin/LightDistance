package pavlo.com.lightdistance.beaconutils;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;

import pavlo.com.lightdistance.LightDistanceActivity;

/**
 * Created by paul on 11/04/16.
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

        for (Beacon currentBeacon : beaconList) {
            int measuredPower = currentBeacon.getRssi();
            if (currentBeacon.getMajor() == beacon1Major) {
                lightDistanceActivity.updateTextView(beacon1TextViewId, measuredPower);
            }
            else if (currentBeacon.getMajor() == beacon2Major) {
                lightDistanceActivity.updateTextView(beacon2TextViewId, measuredPower);
            }
            else if (currentBeacon.getMajor() == beacon3Major) {
                lightDistanceActivity.updateTextView(beacon3TextViewId, measuredPower);
            }
        }
    }
}
