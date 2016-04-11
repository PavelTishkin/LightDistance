package pavlo.com.lightdistance;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.BeaconManager;

import java.util.UUID;

import pavlo.com.lightdistance.beaconutils.LightMonitoringListener;
import pavlo.com.lightdistance.beaconutils.LightServiceReadyCallback;

public class LightDistanceActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private UUID beaconUUID;
    private int beacon1Major;
    private int beacon2Major;
    private int beacon3Major;
    private int beacon1TextViewId;
    private int beacon2TextViewId;
    private int beacon3TextViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_distance);

        beaconUUID = UUID.fromString(getResources().getString(R.string.beacon_group_uuid));

        beacon1Major = getResources().getInteger(R.integer.beacon1_major);
        beacon2Major = getResources().getInteger(R.integer.beacon2_major);
        beacon3Major = getResources().getInteger(R.integer.beacon3_major);

        beacon1TextViewId = R.id.beacon1_distance;
        beacon2TextViewId = R.id.beacon2_distance;
        beacon3TextViewId = R.id.beacon3_distance;

        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new LightMonitoringListener(this, beacon1TextViewId, beacon2TextViewId, beacon3TextViewId, beacon1Major, beacon2Major, beacon3Major));

        beaconManager.connect(new LightServiceReadyCallback(beaconManager, beaconUUID, beacon1Major));
        beaconManager.connect(new LightServiceReadyCallback(beaconManager, beaconUUID, beacon2Major));
        beaconManager.connect(new LightServiceReadyCallback(beaconManager, beaconUUID, beacon3Major));
    }

    public void onRedButtonClick(View view) {
        getWindow().getDecorView().setBackgroundColor(Color.argb(255, 255, 0, 0));
    }

    public void onGreenButtonClick(View view) {
        getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 255, 0));
    }

    public void onBlueButtonClick(View view) {
        getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 0, 255));
    }

    public void updateTextView(int viewId, final int updateText) {

        final TextView textView = (TextView)this.findViewById(viewId);

        runOnUiThread(new Runnable() {
            public void run() {
                textView.setText("" + updateText);
            }
        });
    }
}
