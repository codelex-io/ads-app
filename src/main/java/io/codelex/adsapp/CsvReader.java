package io.codelex.adsapp;

import io.codelex.adsapp.ui.MainController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private List<Ad> ads;

    public CsvReader() {
        ads = new ArrayList<>();
    }

    public List<Ad> parseCsv(String csvPath) {

        AdvertisingNameConverter converter = new AdvertisingNameConverter();

        BufferedReader fileReader = null;

        final String DELIMITER = ",";
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(csvPath));

            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] splitValues = line.split(DELIMITER);

                String date = splitValues[0];
                String programStart = splitValues[1].substring(0, splitValues[1].length() - 3);
                String breakMinutes = splitValues[3];
                String advertisingName = (String) converter.convert(splitValues[4]);

                Ad ad = new Ad(date, programStart, breakMinutes, advertisingName);
                ads.add(ad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ads;
    }
}