package io.github.paveltishkin.lightdistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;
import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.github.paveltishkin.lightdistance.io.github.paveltishkin.lightdistance.model.BeaconDataModel;

public class DiscoveredBeaconsActivity extends AppCompatActivity {

    private ListView beaconsListView;
    private String selectedColor;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovered_beacons);

        selectedColor = getIntent().getStringExtra("beaconColor");
        preferences = getSharedPreferences(getString(R.string.settings_storage), 0);
        String foundBeaconsJSON = preferences.getString(getString(R.string.detected_beacons_key), null);
        Type listType = new TypeToken<ArrayList<Beacon>>(){}.getType();
        List<Beacon> foundBeaconsList = new Gson().fromJson(foundBeaconsJSON, listType);
        BeaconDataModel beaconsData[] = new BeaconDataModel[foundBeaconsList.size()];

        if (foundBeaconsList != null) {
            int i = 0;
            for (Beacon beacon : foundBeaconsList) {
                BeaconDataModel beaconData = new BeaconDataModel(beacon.getProximityUUID().toString(), String.valueOf(beacon.getMajor()), String.valueOf(beacon.getMinor()));
                beaconsData[i] = beaconData;
                //addBeaconToLayout(beacon);
                i++;
            }
            BeaconArrayAdapter adapter = new BeaconArrayAdapter(this,
                    R.layout.fragment_beacon, beaconsData);

            beaconsListView = (ListView)findViewById(R.id.found_beacons_list);
            beaconsListView.setAdapter(adapter);

            beaconsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?>adapter, View v, int position, long i){
                    BeaconDataModel beaconItem = (BeaconDataModel) adapter.getItemAtPosition(position);

                    SharedPreferences.Editor prefsEditor = preferences.edit();

                    String uuidKey = getResources().getString(R.string.beacon_uuid_key) + "_" + selectedColor;
                    prefsEditor.putString(uuidKey, beaconItem.uuid);

                    String majorKey = getResources().getString(R.string.beacon_major_key) + "_" + selectedColor;
                    prefsEditor.putString(majorKey, beaconItem.major);

                    String minorKey = getResources().getString(R.string.beacon_minor_key) + "_" + selectedColor;
                    prefsEditor.putString(minorKey, beaconItem.minor);

                    prefsEditor.commit();

                    finish();
                }
            });
        }
    }

/*    private void addBeaconToLayout(Beacon beacon) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BeaconFragment newBeaconFragment = BeaconFragment.newInstance(null, String.valueOf(beacon.getProximityUUID()), String.valueOf(beacon.getMajor()), String.valueOf(beacon.getMinor()));
        ft.add(R.id.found_beacons_placeholder, newBeaconFragment);
        ft.commit();
    }*/
}
