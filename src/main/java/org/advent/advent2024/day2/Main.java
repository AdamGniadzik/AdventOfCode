package org.advent.advent2024.day2;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long sum = 0;
        try {
            InputStream is = Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day2/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                List<Integer> numbers = new ArrayList<>(Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).boxed()
                        .toList());
                boolean correctArray = isCoorect(numbers);
                if (correctArray) {
                    sum++;
                    System.out.println(numbers);
                    System.out.println();
                }

            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isCoorectReversed(List<Integer> numbers) {
        boolean ascending = numbers.get(numbers.size() - 1) < numbers.get(numbers.size() - 2);
        boolean correctArray = true;
        boolean potentiallyCorrectArray = true;
        int i = numbers.size() - 1;
        while (i > 0) {
            int diff = numbers.get(i) - numbers.get(i - 1);
            if (!(!ascending && diff <= 3 && diff >= 1 || ascending && diff >= -3 && diff <= -1)) {
                if (!potentiallyCorrectArray) {
                    correctArray = false;
                    break;
                } else {
                    potentiallyCorrectArray = false;
                    numbers.remove(i - 1);
                    i--;
                    continue;
                }
            }
            i--;
        }
        return correctArray;
    }

    public static boolean isCoorect(List<Integer> numbers) {
        boolean ascending = numbers.get(0) < numbers.get(1);
        boolean correctArray = true;
        boolean potentiallyCorrectArray = true;
        boolean reversedResult = false;
        int i = 0;
        while (i < numbers.size() - 1) {
            int diff = numbers.get(i + 1) - numbers.get(i);
            if (!(ascending && diff <= 3 && diff >= 1 || !ascending && diff >= -3 && diff <= -1)) {
                if (!potentiallyCorrectArray) {
                    correctArray = false;
                    break;
                } else {
                    potentiallyCorrectArray = false;
                    reversedResult = isCoorectReversed(new ArrayList<>(numbers));
                    if(i <= numbers.size() -2 ){
                        numbers.remove(i + 1);
                    }
                    continue;
                }
            }
            i++;
        }
        return correctArray || reversedResult;
    }
}
