package org.advent.advent2016.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day4/input.txt"));
        List<String> input = new ArrayList<>();
        //421062 too high
        //243668 too low
        // too hjigh 411182
        int score = 0;
        while (br.ready()) {
            input.add(br.readLine());
        }
        stage1(input);
        stage2(input);

    }

    private static void stage1(List<String> input) {
        int score = 0;
        for (String code : input) {
            int index = 0;
            StringBuilder id = new StringBuilder();
            int[] letters = new int[26];
            Map<Character, Integer> map = new HashMap<>();
            char letter = code.charAt(index);
            while (letter != '[') {
                if (letter == '-') {
                    letter = code.charAt(++index);
                    continue;
                }
                if (letter - 'a' < 26 && letter >= 'a') {
                    map.put(letter, map.getOrDefault(letter, 0) + 1);
                    letters[letter - 'a']++;
                } else {
                    id.append(letter);
                }
                letter = code.charAt(++index);
            }
            StringBuilder correctChecksum = new StringBuilder();
            List<Integer> values = map.values().stream().sorted(Comparator.reverseOrder()).toList();
            for (int i = 0; i < 5; i++) {
               for(int j =0;j<letters.length;j++){
                   if(letters[j] == values.get(i)){
                       correctChecksum.append((char)('a' + j));
                       letters[j] = 0;
                       break;
                   }
               }
            }
            StringBuilder inputChecksum = new StringBuilder();
            letter = code.charAt(++index);
            while (letter != ']') {
                inputChecksum.append(letter);
                letter = code.charAt(++index);
            }
            if(inputChecksum.toString().contentEquals(correctChecksum)){
                score += Integer.parseInt(id.toString());
            }

        }
        System.out.println(score);
    }

    private static void stage2(List<String> input) {
        for (String code : input) {
            int index = 0;
            Integer id  = Integer.parseInt(code.substring(code.lastIndexOf("-") + 1, code.indexOf("[")));
            StringBuilder newCode = new StringBuilder();
            char letter = code.charAt(index);
            while (letter != '[') {
                if (letter == '-') {
                    letter = code.charAt(++index);
                    continue;
                }
                if (letter - 'a' < 26 && letter >= 'a') {
                    newCode.append((char) ((((letter - 'a') + id) % 26 ) + 'a'));
                }
                letter = code.charAt(++index);
            }
            if(newCode.toString().contains("northpole")){
                System.out.println(newCode + "  " + id);
            }
        }
    }
}
