package org.advent.advent2024.day1;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long sum = 0;
        long secondSum = 0;
        try {
            InputStream is = Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day1/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<Integer> list1 = new ArrayList<>();
            Set<Integer> set1 = new HashSet<>();
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> list2 = new ArrayList<>();
            while (br.ready()) {
                String[] numbers = br.readLine().split("   ");
                Integer secondNumber = Integer.parseInt(numbers[1]);
                Integer firstNumber = Integer.parseInt(numbers[0]);
                list1.add(firstNumber);
                list2.add(secondNumber);
                set1.add(firstNumber);
                map.put(secondNumber, map.getOrDefault(secondNumber, 0) + 1);
            }
            Collections.sort(list1);
            Collections.sort(list2);

            for (int i = 0; i < list1.size(); i++) {
                sum += Math.abs(list1.get(i) - list2.get(i));
            }
            for (Integer key : set1) {
                secondSum += key * map.getOrDefault(key, 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(secondSum);
    }
}
