package org.advent.advent2023.day1;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("0", 0);
            map.put("1", 1);
            map.put("2", 2);
            map.put("3", 3);
            map.put("4", 4);
            map.put("5", 5);
            map.put("6", 6);
            map.put("7", 7);
            map.put("8", 8);
            map.put("9", 9);
//             Add puts below to complete second part as well.
//            map.put("one", 1);
//            map.put("two", 2);
//            map.put("three", 3);
//            map.put("four", 4);
//            map.put("five", 5);
//            map.put("six", 6);
//            map.put("seven", 7);
//            map.put("eight", 8);
//            map.put("nine", 9);
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day1/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                String line = br.readLine();
                int firstDigit = 0;
                int firstDigitIndex = line.length() - 1;
                int lastDigitIndex = 0;
                int lastDigit = 0;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    int index = line.indexOf(entry.getKey());
                    if (index > -1 && index <= firstDigitIndex) {
                        firstDigitIndex = index;
                        firstDigit = entry.getValue();
                    }
                    index = line.lastIndexOf(entry.getKey());
                    if (index > -1 && index >= lastDigitIndex) {
                        lastDigitIndex = index;
                        lastDigit = entry.getValue();
                    }
                }
                System.out.println(line);
                System.out.println((10 * firstDigit) + lastDigit);
                sum += (10 * firstDigit) + lastDigit;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
