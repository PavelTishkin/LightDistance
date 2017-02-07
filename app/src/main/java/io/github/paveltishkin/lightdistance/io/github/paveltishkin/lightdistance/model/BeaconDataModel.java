package io.github.paveltishkin.lightdistance.io.github.paveltishkin.lightdistance.model;

/**
 * Created by Paul on 06-Feb-17.
 */

public class BeaconDataModel {
    public String uuid;
    public String major;
    public String minor;

    public BeaconDataModel() {
        super();
    }

    public BeaconDataModel(String uuid, String major, String minor) {
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
    }
}
