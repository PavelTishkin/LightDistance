package pavlo.com.lightdistance.beaconutils;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;

import pavlo.com.lightdistance.LightDistanceActivity;

/**
 * Created by paul on 11/04/16.
 */
public class LightMonitoringListener implements BeaconManager.MonitoringListener {

    LightDistanceActivity lightDistanceActivity;
    int beacon1TextViewId;
    int beacon2TextViewId;
    int beacon3TextViewId;
    int beacon1Major;
    int beacon2Major;
    int beacon3Major;

    public LightMonitoringListener (LightDistanceActivity lightDistanceActivity, int beacon1TextViewId, int beacon2TextViewId, int beacon3TextViewId,
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
    public void onEnteredRegion(Region region, List<Beacon> beacon_list) {
        for (Beacon current_beacon : beacon_list) {
            int measuredPower = current_beacon.getMeasuredPower();
            if (current_beacon.getMajor() == beacon1Major) {
                lightDistanceActivity.updateTextView(beacon1TextViewId, measuredPower);
            }
            else if (current_beacon.getMajor() == beacon2Major) {
                lightDistanceActivity.updateTextView(beacon2TextViewId, measuredPower);
            }
            else if (current_beacon.getMajor() == beacon3Major) {
                lightDistanceActivity.updateTextView(beacon2TextViewId, measuredPower);
            }
        }
    }

    @Override
    public void onExitedRegion(Region region) {
        lightDistanceActivity.updateTextView(beacon1TextViewId, 0);
        lightDistanceActivity.updateTextView(beacon2TextViewId, 0);
        lightDistanceActivity.updateTextView(beacon3TextViewId, 0);
    }
}
