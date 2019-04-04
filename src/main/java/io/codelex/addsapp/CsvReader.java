package io.codelex.addsapp;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReader {

    private static final String csvPath =
            "/home/davis/Downloads/Playlist_ReTV010419_generated280320190336.csv";

    public List<Ad> parseCsv() throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPath))
        ) {
            CsvToBean<Ad> csvToBean = new CsvToBeanBuilder<Ad>(reader)
                    .withType(Ad.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .build();

            return csvToBean.parse();


        }

    }
}
