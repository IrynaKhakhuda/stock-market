package com.stock.market.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
class ExtractHolidaysServiceTest {

    @Autowired
    ExtractHolidaysService extractHolidaysServiceTest;

    List<LocalDate> expected = fillExpectedDates();

    @Test
    void getHolidaysTest() {
        List<LocalDate> actual = extractHolidaysServiceTest.getHolidays("SAMPLE");
        assertEquals(expected, actual);
    }

    private List<LocalDate> fillExpectedDates() {
        List<LocalDate> valuesRange = new ArrayList<>();
        valuesRange.add(LocalDate.parse("2022-07-04"));
        valuesRange.add(LocalDate.parse("2022-07-07"));
        valuesRange.add(LocalDate.parse("2022-07-08"));
        return valuesRange;
    }
}