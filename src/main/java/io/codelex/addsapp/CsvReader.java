package io.codelex.addsapp;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.ArrayList;

public class CsvReader {
    
    public static ArrayList<String> parseCsv(String myCsv) throws
            CsvDataTypeMismatchException, 
            CsvConstraintViolationException {
        AdvertisingNameConverter converter = new AdvertisingNameConverter();

        ArrayList<String> ads = new ArrayList<>();

        if (myCsv != null) {
            String[] splitData = myCsv.split("\\s*,\\s*");
            for (String split : splitData) {
                assert split != null;
                if (split.contains("Lcom_M")) {
                    split = (String) converter.convert(split);
                }
                ads.add(split.trim());
            }
        }
        return ads;
    }
}