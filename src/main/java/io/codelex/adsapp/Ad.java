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
}