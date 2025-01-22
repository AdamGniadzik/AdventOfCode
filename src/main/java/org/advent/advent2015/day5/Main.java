package org.advent.advent2015.day5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    static Set<String> disabledWords = Set.of("ab", "cd", "pq", "xy");
    static Set<Character> vowelsSet = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day5/input.txt"));
        int counterStage1 = 0;
        int counterStage2 = 0;
        while (br.ready()) {
            String input = br.readLine();
            if (isNice(input)) {
                counterStage1++;
            }
            if (isNice2(input)) {
                counterStage2++;
            }
        }
        System.out.println(counterStage1);
        System.out.println(counterStage2);
    }

    public static boolean isNice(String input) {
        for (String word : disabledWords) {
            if (input.contains(word)) {
                return false;
            }
        }
        char previousLetter = ' ';
        int vowels = 0;
        boolean twiceInRow = false;
        for (char ch : input.toCharArray()) {
            if (ch == previousLetter) {
                twiceInRow = true;
            }
            previousLetter = ch;
            if (vowelsSet.contains(ch)) {
                vowels++;
            }
        }
        return vowels >= 3 && twiceInRow;
    }

    public static boolean isNice2(String input) {
        char[] charArr = input.toCharArray();
        boolean repeatedWithOneBetween = false;
        boolean twoValidPairs = false;
        char previous = ' ';
        for (int i = 0; i < charArr.length; i++) {
            if (i >= 2) {
                if (previous == charArr[i]) {
                    repeatedWithOneBetween = true;
                    break;
                }
                previous = charArr[i - 1];
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < charArr.length; i++) {
            if(i+2 > charArr.length){
                break;
            }
            String substring = input.substring(i, i + 2);
            if(map.containsKey(substring)){
                if(i - map.get(substring) > 1){
                    twoValidPairs = true;
                }
            }else{
                map.put(substring, i);
            }
        }

        return repeatedWithOneBetween && twoValidPairs;
    }
}
