package io.codelex.adsapp;

import com.opencsv.bean.AbstractBeanField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvertisingNameConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) {
        Pattern pattern = Pattern.compile("(?<=_)(M.*?)(?=_)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group().trim();
        }
        return value;
    }
}