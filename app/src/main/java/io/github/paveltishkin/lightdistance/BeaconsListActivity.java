package io.github.paveltishkin.lightdistance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Pavel Tishkin on 25/04/16.
 */
public class BeaconsListActivity extends AppCompatActivity {

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_item_layout);

        if (findViewById(R.id.beacon_item_frame_layout) != null) {

            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            BeaconFragment redBeaconFragment = BeaconFragment.newInstance("Red");
            ft.replace(R.id.red_beacon_placeholder, redBeaconFragment);
            ft.commit();

            ft = getSupportFragmentManager().beginTransaction();
            BeaconFragment greenBeaconFragment = BeaconFragment.newInstance("Green");
            ft.replace(R.id.green_beacon_placeholder, greenBeaconFragment);
            ft.commit();

            ft = getSupportFragmentManager().beginTransaction();
            BeaconFragment blueBeaconFragment = BeaconFragment.newInstance("Blue");
            ft.replace(R.id.blue_beacon_placeholder, blueBeaconFragment);
            ft.commit();
        }

        settings = getSharedPreferences(getString(R.string.settings_storage), 0);
    }

    @Override
    public void onStart() {
        super.onStart();
//        ((TextView)this.findViewById(R.id.editMajor1)).setText(settings.getInt("Major1", 0) + "");
//        ((TextView)this.findViewById(R.id.editMajor2)).setText(settings.getInt("Major2", 0) + "");
//        ((TextView)this.findViewById(R.id.editMajor3)).setText(settings.getInt("Major3", 0) + "");
    }

    public void onSaveButtonClick(View view) {
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("Major1", Integer.parseInt(((TextView) this.findViewById(R.id.editMajor1)).getText().toString()));
        editor.putInt("Major2", Integer.parseInt(((TextView) this.findViewById(R.id.editMajor2)).getText().toString()));
        editor.putInt("Major3", Integer.parseInt(((TextView) this.findViewById(R.id.editMajor3)).getText().toString()));

        editor.commit();

        finish();
    }

    public void onCancelButtonClick(View view) {
        finish();
    }
}
