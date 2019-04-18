package io.codelex.adsapp;

import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String> parseCsv(String myCsv) {
        AdvertisingNameConverter converter = new AdvertisingNameConverter();

        List<String> ads = new ArrayList<>();

        if (myCsv != null) {
            String[] splitData = myCsv.split("\\s*,\\s*");
            for (String split : splitData) {
                assert split != null;
                if (split.contains("Lcom_M")) {
                    split = (String) converter.convert(split);
                }
                ads.add(split.trim());
                System.out.println(ads.get(0));
            }
        }
        return ads;
    }
}