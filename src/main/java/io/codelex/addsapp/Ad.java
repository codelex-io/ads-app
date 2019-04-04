package io.codelex.addsapp;

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