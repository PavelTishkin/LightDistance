package io.github.paveltishkin.lightdistance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BeaconFragment extends Fragment {

    private String beaconColorKey = null;

    public BeaconFragment() {
        // Required empty public constructor
    }

    public static BeaconFragment newInstance(String beaconColorKey) {
        BeaconFragment beaconFragment = new BeaconFragment();
        Bundle args = new Bundle();
        args.putString("beacon_color_key", beaconColorKey);
        beaconFragment.setArguments(args);
        return beaconFragment;
    }

    public static BeaconFragment newInstance(String beaconColorKey, String uuid, String major, String minor) {
        BeaconFragment beaconFragment = new BeaconFragment();
        Bundle args = new Bundle();
        args.putString("beacon_color_key", beaconColorKey);
        args.putString("uuid", uuid);
        args.putString("major", major);
        args.putString("minor", minor);
        beaconFragment.setArguments(args);
        return beaconFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beacon, container, false);
        SharedPreferences sharedPreferences =  getContext().getSharedPreferences(getString(R.string.settings_storage), 0);

        final TextView colorNameTextView = (TextView) rootView.findViewById(R.id.beacon_color_label_id);
        TextView uuidTextView = (TextView) rootView.findViewById(R.id.beacon_uuid_value_id);
        TextView majorTextView = (TextView) rootView.findViewById(R.id.beacon_major_value_id);
        TextView minorTextView = (TextView) rootView.findViewById(R.id.beacon_minor_value_id);

        if (savedInstanceState == null) {
            String colorName = (String) getArguments().getString("beacon_color_key");

            if (null != colorName) {
                colorNameTextView.setText(colorName);
                String uuidKey = getResources().getString(R.string.beacon_uuid_key) + "_" + colorName.toLowerCase();
                String uuidValue = sharedPreferences.getString(uuidKey, "");
                uuidTextView.setText(uuidValue);
                String majorKey = getResources().getString(R.string.beacon_major_key) + "_" + colorName.toLowerCase();
                String majorValue = sharedPreferences.getString(majorKey, "");
                majorTextView.setText(majorValue);
                String minorKey = getResources().getString(R.string.beacon_minor_key) + "_" + colorName.toLowerCase();
                String minorValue = sharedPreferences.getString(minorKey, "");
                minorTextView.setText(minorValue);
            } else {
                String uuid = (String) getArguments().getString("uuid");
                String major = (String) getArguments().getString("major");
                String minor = (String) getArguments().getString("minor");
                uuidTextView.setText(uuid);
                majorTextView.setText(major);
                minorTextView.setText(minor);
            }
        }

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DiscoveredBeaconsActivity.class);
                i.putExtra("beaconColor", (String) getArguments().getString("beacon_color_key").toLowerCase());
                startActivity(i);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
