package com.example.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {

            List<String> words = reader.lines()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> word.matches("[а-я]{3,}")) 
                    .distinct()
                    .collect(Collectors.toList());

            Map<String, Set<String>> anagramsMap = new HashMap<>();
            for (String word : words) {
                String key = sortWord(word); 
                anagramsMap
                        .computeIfAbsent(key, k -> new TreeSet<>()) 
                        .add(word);
            }

            return anagramsMap.values().stream()
                    .filter(group -> group.size() > 1) 
                    .sorted(Comparator.comparing(group -> group.iterator().next()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
