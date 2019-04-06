package io.codelex.adsapp;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ad {

    @CsvCustomBindByPosition(position = 0, converter = LocalDateConverter.class)
    private LocalDate date;

    @CsvCustomBindByPosition(position = 1, converter = LocalTimeConverter.class)
    private LocalTime programStart;

    @CsvBindByPosition(position = 3)
    private int breakMinutes;

    @CsvCustomBindByPosition(position = 4, converter = AdvertisingNameConverter.class)
    private String advertisingName;

    public Ad() {
    }

    public Ad(LocalDate date, LocalTime programStart, int breakMinutes, String advertisingName) {
        this.date = date;
        this.programStart = programStart;
        this.breakMinutes = breakMinutes;
        this.advertisingName = advertisingName;
    }

    LocalDate getDate() {
        return date;
    }

    LocalTime getProgramStart() {
        return programStart;
    }

    int getBreakMinutes() {
        return breakMinutes;
    }

    String getAdvertisingName() {
        return advertisingName;
    }

}