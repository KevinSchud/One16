package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestCombineWordsService {

    @Autowired
    private CombineWordsService combineWordsService;

    @Test
    void testCombineWordsFromFileAndSorted() {
        List<String> result = combineWordsService.combineWordsPresentInFile(new File("./src/test/resources/test.txt"), 7);
        List<String> expected = new ArrayList<>();
        expected.add("buzz + cut = buzzcut");
        expected.add("p + uzzled = puzzled");
        assertEquals(expected, result);
    }

}
