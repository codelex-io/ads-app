package io.codelex.adsapp;

public class Ad {

    private String date;
    private String programStart;
    private String breakMinutes;
    private String advertisingName;

    Ad(String date, String programStart, String breakMinutes, String advertisingName) {
        this.date = date;
        this.programStart = programStart;
        this.breakMinutes = breakMinutes;
        this.advertisingName = advertisingName;
    }

    String getDate() {
        return date;
    }

    String getProgramStart() {
        return programStart;
    }

    String getBreakMinutes() {
        return breakMinutes;
    }

    String getAdvertisingName() {
        return advertisingName;
    }

    void setDate(String date) {
        this.date = date;
    }

    void setProgramStart(String programStart) {
        this.programStart = programStart;
    }

    void setBreakMinutes(String breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    void setAdvertisingName(String advertisingName) {
        this.advertisingName = advertisingName;
    }

}