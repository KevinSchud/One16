package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KevinSch
 * @since 18/06/2021
 */
@Service
public class CombineWordsService {

    public List<String> combineWordsTest(File file) {
        List<String> parts, sixLetterWords;

        List<String> results = new ArrayList<>();
        List<String> words = getWordsOutOfFile(file);

        words = words.stream()
                .distinct()
                .collect(Collectors.toList());

        sixLetterWords = words.stream()
                .filter(s -> s.length() == 6)
                .collect(Collectors.toList());
        parts = words.stream()
                .filter(s -> s.length() != 6)
                .collect(Collectors.toList());

        for (String part : parts) {
            int partLength = part.length();
            List<String> common = new ArrayList<>(sixLetterWords);
            List<String> combinationsToCheck = parts.stream()
                    .filter(s -> s.length() == (6 - partLength))
                    .map(s -> part + s)
                    .collect(Collectors.toList());
            common.retainAll(combinationsToCheck);
            for (String validWord : common) {
                results.add(part + " + " + validWord.substring(partLength) + " = " + validWord);
            }
        }

        return results.stream()
                .sorted(Comparator.comparing(s -> StringUtils.substringAfterLast(String.valueOf(s), "= "))
                        .thenComparing(s -> StringUtils.substringBefore(String.valueOf(s), " +").length()))
                .collect(Collectors.toList());
    }

    private List<String> getWordsOutOfFile(File file) {
        List<String> words = new ArrayList<>();
        String line;
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))){
            while ((line = buffer.readLine()) != null) {
                words.add(line.replace(" ", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
