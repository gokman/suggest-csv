package com.gokman.model;

/**
 * This model is used for response to the user.
 * It is filtered from Suggest class
 */
public class Output {

    private long _id;
    private String name;
    private String type;
    private double latitude;
    private double longitude;


    public Output(long _id, String name, String type, double latitude, double longitude) {
        this._id       = _id;
        this.name      = name;
        this.type      = type;
        this.latitude  = latitude;
        this.longitude = longitude;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return  _id      + " " +
                name     + " " +
                type     + " " +
                latitude + " " +
                longitude;
    }
}
