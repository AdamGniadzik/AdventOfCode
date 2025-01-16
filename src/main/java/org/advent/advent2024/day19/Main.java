package org.advent.advent2024.day19;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day19/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<String> possiblePatterns = new ArrayList<>();
            while (br.ready()) {
                String input = br.readLine();
                if (input.isEmpty()) {
                    break;
                }
                possiblePatterns.addAll(Arrays.stream(input.split(", ")).toList());
            }
            int counterStage1 = 0;
            long counterStage2 = 0L;
            while (br.ready()) {
                String currentPattern = br.readLine();
                //Stage 1
                Set<String> newPatterns = processPatterns(Set.of(currentPattern), possiblePatterns);
                while (!newPatterns.isEmpty()) {
                    if (newPatterns.contains("")) {
                        counterStage1++;
                        break;
                    }
                    newPatterns = processPatterns(newPatterns, possiblePatterns);
                }
                // Stage 2
                Map<String, Long> newPatternsMap = processPatternsToMap(Map.of(currentPattern, 1l), possiblePatterns);
                while (!newPatternsMap.isEmpty()) {
                    if (newPatternsMap.containsKey("")) {
                        counterStage2 += newPatternsMap.get("");
                        newPatternsMap.remove("");
                    }
                    newPatternsMap = processPatternsToMap(newPatternsMap, possiblePatterns);
                }
            }
            System.out.println(counterStage1);
            System.out.println(counterStage2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<String> processPatterns(Set<String> patternsToProcess, List<String> possiblePatterns) {
        Set<String> newPatterns = new HashSet<>();
        for (String pattern : patternsToProcess) {
            for (String possiblePattern : possiblePatterns) {
                if (pattern.startsWith(possiblePattern)) {
                    newPatterns.add(pattern.substring(possiblePattern.length()));
                }
            }
        }
        return newPatterns;
    }

    public static Map<String, Long> processPatternsToMap(Map<String, Long> patternsToProcess, List<String> possiblePatterns) {
        Map<String, Long> newPatterns = new HashMap<>();
        for (String pattern : patternsToProcess.keySet()) {
            for (String possiblePattern : possiblePatterns) {
                if (pattern.startsWith(possiblePattern)) {
                    String newPattern = pattern.substring(possiblePattern.length());
                    if (newPatterns.containsKey(newPattern)) {
                        newPatterns.put(newPattern, newPatterns.get(newPattern) + patternsToProcess.get(pattern));
                    } else {
                        newPatterns.put(newPattern, patternsToProcess.get(pattern));

                    }
                }
            }
        }
        return newPatterns;
    }
}