package io.codelex.adsapp;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class CsvReader {

   public List<Ad> parseCsv(Reader reader) {
       
        CsvToBean<Ad> csvToBean = new CsvToBeanBuilder<Ad>(reader)
                .withType(Ad.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();

        return csvToBean.parse();
    }
}

