package io.codelex.adsapp;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AdvertisingNameConverter extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        Pattern pattern = Pattern.compile("(?<=_)(M.*?)(?=_)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find())
        {
            return matcher.group();
        }
        return value;
    }
}