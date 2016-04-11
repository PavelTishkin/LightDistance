package pavlo.com.lightdistance.beaconutils;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.UUID;

/**
 * Created by paul on 11/04/16.
 */
public class LightServiceReadyCallback implements BeaconManager.ServiceReadyCallback {
    BeaconManager beaconManager;
    UUID beaconUUID;
    Integer beaconMajor;

    public LightServiceReadyCallback(BeaconManager beaconManager, UUID beaconUUID, Integer beaconMajor) {
        this.beaconManager = beaconManager;
        this.beaconUUID = beaconUUID;
        this.beaconMajor = beaconMajor;
    }

    @Override
    public void onServiceReady() {
        beaconManager.startMonitoring(new Region("Light source",
                beaconUUID,
                beaconMajor,
                null));
    }
}
