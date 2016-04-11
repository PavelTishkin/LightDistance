package pavlo.com.lightdistance;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LightDistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_distance);
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
}
