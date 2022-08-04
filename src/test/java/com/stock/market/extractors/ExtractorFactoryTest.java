package com.stock.market.extractors;

import com.stock.market.services.ExistedFiles;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
class ExtractorFactoryTest {

    @Autowired
    ExtractorFactory extractorFactoryTest;


    @Test
    void getCoincidentExtractorPeriodicalExtractorTest() {
        assertEquals(PeriodicalExtractor.class, extractorFactoryTest.getCoincidentExtractor(2000).getClass());
    }

    @Test
    void getCoincidentExtractorAlgorithmicExtractorTest() {
        assertEquals(AlgorithmicExtractor.class, extractorFactoryTest.getCoincidentExtractor(1000).getClass());
    }

    @Test
    void getCoincidentExtractorSimpleExtractorTest() {
        assertEquals(SimpleExtractor.class, extractorFactoryTest.getCoincidentExtractor(500).getClass());
    }

    @Test
    void getCoincidentExtractorShortRangeExtractorTest() {
        assertEquals(ShortRangeExtractor.class, extractorFactoryTest.getCoincidentExtractor(10).getClass());
    }
}