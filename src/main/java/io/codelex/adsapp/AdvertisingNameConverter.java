package io.codelex.adsapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AdvertisingNameConverter {

    Object convert(String value) {
        Pattern pattern = Pattern.compile("(?<=_)(M.*?)(?=_)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group();
        }
        return value;
    }
}