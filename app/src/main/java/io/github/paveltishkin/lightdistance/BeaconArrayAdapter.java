package io.github.paveltishkin.lightdistance;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.github.paveltishkin.lightdistance.io.github.paveltishkin.lightdistance.model.BeaconDataModel;

/**
 * Created by Paul on 06-Feb-17.
 */

public class BeaconArrayAdapter extends ArrayAdapter<BeaconDataModel> {
    Context context;
    int layoutResourceId;
    BeaconDataModel beaconsData[] = null;

    public BeaconArrayAdapter(Context context, int layoutResourceId, BeaconDataModel[] beaconsData) {
        super(context, layoutResourceId, beaconsData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.beaconsData = beaconsData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BeaconDataHolder dataHolder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            dataHolder = new BeaconDataHolder();
            dataHolder.uuidTextView = (TextView) row.findViewById(R.id.beacon_uuid_value_id);
            dataHolder.majorTextView = (TextView) row.findViewById(R.id.beacon_major_value_id);
            dataHolder.minorTextView = (TextView) row.findViewById(R.id.beacon_minor_value_id);

            row.setTag(dataHolder);
        } else {
            dataHolder = (BeaconDataHolder) convertView.getTag();
        }

        BeaconDataModel dataModel = beaconsData[position];
        dataHolder.uuidTextView.setText(dataModel.uuid);
        dataHolder.majorTextView.setText(dataModel.major);
        dataHolder.minorTextView.setText(dataModel.minor);

        return row ;
    }

    private static class BeaconDataHolder {
        TextView uuidTextView;
        TextView majorTextView;
        TextView minorTextView;
    }
}
