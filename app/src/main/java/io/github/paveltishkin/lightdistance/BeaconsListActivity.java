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
        ((TextView)this.findViewById(R.id.editMajor1)).setText(settings.getString("Major1", ""));
        ((TextView)this.findViewById(R.id.editMajor2)).setText(settings.getString("Major2", ""));
        ((TextView)this.findViewById(R.id.editMajor3)).setText(settings.getString("Major3", ""));
    }

    public void onSaveButtonClick(View view) {
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("Major1", ((TextView)this.findViewById(R.id.editMajor1)).getText().toString());
        editor.putString("Major2", ((TextView)this.findViewById(R.id.editMajor2)).getText().toString());
        editor.putString("Major3", ((TextView)this.findViewById(R.id.editMajor3)).getText().toString());

        editor.commit();

        finish();
    }

    public void onCancelButtonClick(View view) {
        finish();
    }

}
