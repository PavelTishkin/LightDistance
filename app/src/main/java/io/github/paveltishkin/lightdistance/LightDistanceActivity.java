package io.github.paveltishkin.lightdistance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

public class LightDistanceActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private Region allBeaconsRegion;
    private UUID beaconUUID;
    private int beacon1TextViewId;
    private int beacon2TextViewId;
    private int beacon3TextViewId;
    private LightRangingListener lightRangingListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_distance);

        SharedPreferences settings = getSharedPreferences(getString(R.string.settings_storage), 0);
        int beacon1Major = settings.getInt("Major1", 0);
        int beacon2Major = settings.getInt("Major2", 0);
        int beacon3Major = settings.getInt("Major3", 0);

        beaconUUID = UUID.fromString(getResources().getString(R.string.beacon_group_uuid));

        beacon1TextViewId = R.id.beacon1_distance;
        beacon2TextViewId = R.id.beacon2_distance;
        beacon3TextViewId = R.id.beacon3_distance;

        allBeaconsRegion = new Region("Light Source", beaconUUID, null, null);

        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setBackgroundScanPeriod(1000, 5000);

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> beaconList) {

            }

            @Override
            public void onExitedRegion(Region region) {
                updateTextView(beacon1TextViewId, 0);
                updateTextView(beacon2TextViewId, 0);
                updateTextView(beacon3TextViewId, 0);
            }
        });
        lightRangingListener = new LightRangingListener(this, beacon1TextViewId, beacon2TextViewId, beacon3TextViewId, beacon1Major, beacon2Major, beacon3Major);

        beaconManager.setRangingListener(lightRangingListener);
    }

    @Override
    public void onStart() {
        super.onStart();

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(allBeaconsRegion);
                beaconManager.startRanging(allBeaconsRegion);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        beaconManager.stopMonitoring(allBeaconsRegion);
        beaconManager.stopRanging(allBeaconsRegion);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onSettingsButtonClick(View view) {
        Intent i = new Intent(getApplicationContext(), BeaconsListActivity.class);
        startActivity(i);
    }

    public void updateTextView(int viewId, final int updateText) {

        final TextView textView = (TextView)this.findViewById(viewId);

        runOnUiThread(new Runnable() {
            public void run() {
                textView.setText("" + updateText);
            }
        });
    }

    public void updateBackgroundColor(final int red, final int green, final int blue) {
        runOnUiThread(new Runnable() {
            public void run() {
                getWindow().getDecorView().setBackgroundColor(Color.argb(255, red, green, blue));
            }
        });
    }
}
