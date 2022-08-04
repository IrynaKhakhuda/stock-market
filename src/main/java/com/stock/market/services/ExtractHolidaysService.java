package com.stock.market.services;

import com.stock.market.extractors.ExtractorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExtractHolidaysService {

    @Autowired
    FileLoader fileLoader;

    @Autowired
    ExtractorFactory extractorFactory;

    public List<LocalDate> getHolidays(String token) {
        List<LocalDate> allDates = fileLoader.readFile(token);
        return extractorFactory.getCoincidentExtractor(allDates.size()).extractHolidays(allDates);
    }


}
