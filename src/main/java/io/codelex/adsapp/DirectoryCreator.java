package io.codelex.adsapp;

import java.io.*;

import static io.codelex.adsapp.CsvReader.parseCsv;

public class DirectoryCreator {

    public void createDirectories(File directory, BufferedReader bufferedReader) {
        try {
            bufferedReader.readLine();

            String parsedLine = bufferedReader.readLine();
            String dateFromCsv = parseCsv(parsedLine).get(0);
            File parentDirectory = new File(directory
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
