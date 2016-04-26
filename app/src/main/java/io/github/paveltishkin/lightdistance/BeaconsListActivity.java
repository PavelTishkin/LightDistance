package io.github.paveltishkin.lightdistance;

import android.content.SharedPreferences;
import android.os.Bundle;
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
        setContentView(R.layout.activity_beacons_list);

        settings = getSharedPreferences(getString(R.string.settings_storage), 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView)this.findViewById(R.id.editMajor1)).setText(settings.getInt("Major1", 0) + "");
        ((TextView)this.findViewById(R.id.editMajor2)).setText(settings.getInt("Major2", 0) + "");
        ((TextView)this.findViewById(R.id.editMajor3)).setText(settings.getInt("Major3", 0) + "");
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
