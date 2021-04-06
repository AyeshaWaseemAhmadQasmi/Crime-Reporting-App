package com.example.madproject;

public class Crimes {
    private Integer pk_id;
    private String crimename;
    private String desc;
    private String offense_type;
    private String severity_type;
    private String latitude;
    private String longitude;

    public Crimes(Integer pk_id, String crimename, String desc, String offense_type, String severity_type, String latitude, String longitude) {
        this.pk_id = pk_id;
        this.crimename = crimename;
        this.desc = desc;
        this.offense_type = offense_type;
        this.severity_type = severity_type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getPk_id() {
        return pk_id;
    }

    public void setPk_id(Integer pk_id) {
        this.pk_id = pk_id;
    }

    public String getCrimename() {
        return crimename;
    }

    public void setCrimename(String crimename) {
        this.crimename = crimename;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOffense_type() {
        return offense_type;
    }

    public void setOffense_type(String offense_type) {
        this.offense_type = offense_type;
    }

    public String getSeverity_type() {
        return severity_type;
    }

    public void setSeverity_type(String severity_type) {
        this.severity_type = severity_type;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Crimes{" +
                "pk_id=" + pk_id +
                ", crimename='" + crimename + '\'' +
                ", desc='" + desc + '\'' +
                ", offense_type='" + offense_type + '\'' +
                ", severity_type='" + severity_type + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
