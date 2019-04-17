package io.codelex.addsapp;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CsvReaderTest {

    private final File file = new File("./src/test/resources/test_file.csv");

    @Test
    public void should_return_true_if_csv_file_found() {
        //then
        assertTrue(file.exists());

    }

    @Test
    public void should_return_false_if_csv_file_not_found() {
        //when
        File file = new File("./src/test/resources/doesnt_exist.csv");
        //then
        assertFalse(file.exists());

    }

    @Test
    public void should_parse_test_file() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        //when
        ArrayList<String> ads = CsvReader.parseCsv("14.04.19.,06:00:00,Latvijas st�sti (arh�vs) - 1,265,Lcom_M09803_Septanazal sep 15sek LV,00:15,1");
        //then
        assertEquals("14.04.19.", ads.get(0));
        assertEquals("06:00:00", ads.get(1));
        assertEquals("265", ads.get(3));
        assertEquals("M09803", ads.get(4));

    }
    
}