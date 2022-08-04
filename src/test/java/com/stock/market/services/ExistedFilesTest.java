package com.stock.market.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
class ExistedFilesTest {

    @Autowired
    ExistedFiles existedFilesTest;

    @Test
    void isFileExistTest() {
        assertTrue(existedFilesTest.isFileExist("SAMPLE"));
    }

    @Test
    void isFileNotExistTest() {
        assertFalse(existedFilesTest.isFileExist("NP"));
    }

    @Test
    void getPathTest() {
        Path expected = Path.of("src/test/resources/quotes/SAMPLE.csv");
        Path actual = existedFilesTest.getPath("SAMPLE");
        assertEquals(expected, actual);
    }

    @Test
    void getAllTokensTest() {
        Set<String> expectedTokens = new HashSet<>() {{add("SAMPLE");}};
        Set<String> actualTokens = existedFilesTest.getAllTokens();
        assertEquals(expectedTokens, actualTokens);
    }
}