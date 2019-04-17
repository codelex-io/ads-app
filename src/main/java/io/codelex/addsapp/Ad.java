package io.codelex.addsapp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ad {

    private LocalDate date;
    private LocalTime programStart;
    private int breakMinutes;
    private String advertisingName;

    public Ad(LocalDate date, LocalTime programStart,
              int breakMinutes, String advertisingName) {
        super();
        this.date = date;
        this.programStart = programStart;
        this.breakMinutes = breakMinutes;
        this.advertisingName = advertisingName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getProgramStart() {
        return programStart;
    }

    public void setProgramStart(LocalTime programStart) {
        this.programStart = programStart;
    }

    public int getBreakMinutes() {
        return breakMinutes;
    }

    public void setBreakMinutes(int breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    public String getAdvertisingName() {
        return advertisingName;
    }

    public void setAdvertisingName(String advertisingName) {
        this.advertisingName = advertisingName;
    }

    @Override
    public String toString() {
        return "Ad [date=" + date + ", programStart=" + programStart
                + ", breakMinutes=" + breakMinutes + ", advertisingName=" + advertisingName + "]";
    }    
}