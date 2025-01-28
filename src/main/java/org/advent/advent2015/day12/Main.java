package org.advent.advent2015.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day12/input.txt"));
        Pattern integerPattern = Pattern.compile("-?\\d+");
        String input = br.readLine();
        int result = 0;
        Matcher integerMatcher = integerPattern.matcher(input);
        while (integerMatcher.find()) {
            result += Integer.parseInt(integerMatcher.group());
        }
        System.out.println(result);
        int indexOf = input.indexOf(":\"red\"");
        Map<Integer, Integer> redCountersFromIndex = new HashMap<>();

        while (indexOf != -1) {
            int redCounter = 0;
            int startingBracket = indexOf;
            int endingBracket = indexOf;
            int bracketsToFind = 1;
            while (bracketsToFind > 0) {
                if (input.charAt(endingBracket) == '}') {
                    bracketsToFind--;
                } else if (input.charAt(endingBracket) == '{') {
                    bracketsToFind++;
                }
                endingBracket++;
            }
            bracketsToFind = 1;
            while (bracketsToFind > 0) {
                if (input.charAt(startingBracket) == '{') {
                    bracketsToFind--;
                } else if (input.charAt(startingBracket) == '}') {
                    bracketsToFind++;
                }
                startingBracket--;
            }
            String objectWithRed = input.substring(startingBracket + 1, endingBracket);
            integerMatcher = integerPattern.matcher(objectWithRed);
            while (integerMatcher.find()) {
                int intValue = Integer.parseInt(integerMatcher.group());
                result -= intValue;
                redCounter += intValue;
            }
            redCountersFromIndex.put(startingBracket, redCounter);
            for (Integer key : new HashSet<>(redCountersFromIndex.keySet())) {
                if (key > startingBracket) {
                    result += redCountersFromIndex.get(key);
                    redCountersFromIndex.remove(key);
                }
            }
            indexOf = input.indexOf(":\"red\"", endingBracket + 1);

        }
        System.out.println(result);
    }
}

