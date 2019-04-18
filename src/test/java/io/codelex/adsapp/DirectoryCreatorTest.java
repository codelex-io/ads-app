package io.codelex.adsapp;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.codelex.adsapp.CsvReader.parseCsv;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectoryCreatorTest {

    private Path testDir = Files.createTempDirectory("directories");
    private BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("./src/test/resources/test_file.csv"), StandardCharsets.UTF_8);

    public DirectoryCreatorTest() throws IOException {
    }


    @Test
    public void should_create_directories_and_subdirectories_and_skip_if_present() throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        //when
        try {
            bufferedReader.readLine();

            String parsedLine = bufferedReader.readLine();
            String dateFromCsv = parseCsv(parsedLine).get(0);
            File parentDirectory = new File(testDir
                    + dateFromCsv);
            boolean wasSuccessful = parentDirectory.mkdir();
            if (!wasSuccessful) {
                return;
            }

            while ((parsedLine = bufferedReader.readLine()) != null) {
                String timeFromCsv = parseCsv(parsedLine).get(1);
                String breakMinutes = parseCsv(parsedLine).get(3);

                File subDirectory = new File(parentDirectory + "/"
                        + timeFromCsv.substring(0, timeFromCsv.length() - 3) + "("
                        + breakMinutes + ")");
                if (!subDirectory.exists()) {
                    boolean wasSuccessful1 = subDirectory.mkdirs();
                    if (!wasSuccessful1) {
                        return;
                    }
                }
                //then
                assertTrue(subDirectory.exists());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}