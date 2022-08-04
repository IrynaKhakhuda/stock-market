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

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
class FileLoaderTest {


    @Autowired
    FileLoader fileLoaderTest;

    List<LocalDate> expected = fillExpectedDates();

    @Test
    void readFileTest() {
        List<LocalDate> actual =  fileLoaderTest.readFile("SAMPLE");
        assertEquals(expected, actual);
    }


    private List<LocalDate> fillExpectedDates(){
        List<LocalDate> valuesRange = new ArrayList<>();
        valuesRange.add(LocalDate.parse("2022-07-01"));
        valuesRange.add(LocalDate.parse("2022-07-05"));
        valuesRange.add(LocalDate.parse("2022-07-06"));
        valuesRange.add(LocalDate.parse("2022-07-11"));
        valuesRange.add(LocalDate.parse("2022-07-12"));
        valuesRange.add(LocalDate.parse("2022-07-13"));
        valuesRange.add(LocalDate.parse("2022-07-14"));
        valuesRange.add(LocalDate.parse("2022-07-15"));

        return valuesRange;
    }
}