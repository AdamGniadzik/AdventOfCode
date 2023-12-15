package org.advent.advent2023.day13;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day13/input.txt");
        Scanner scanner = new Scanner(is);
        List<List<String>> patterns = new ArrayList<>();
        int sum = 0;
        while (scanner.hasNext()) {
            List<String> pattern = new ArrayList<>();
            String line = scanner.nextLine();
            while (!line.equals("")) {
                pattern.add(line);
                if (scanner.hasNext()) {
                    line = scanner.nextLine();
                } else {
                    line = "";
                }
            }
            patterns.add(pattern);
            int horizontal = checkHorizontal(pattern);
            int vertical = checkVertical(pattern);
            if (vertical != -1) {
                sum += vertical;
            } else {
                sum += horizontal * 100;
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
    }

    public static Integer checkHorizontal(List<String> pattern) {
        List<Integer> possible = new ArrayList<>();
        String line = "";
        for (int i = 0; i < pattern.size(); i++) {
            String nextLine = pattern.get(i);
            if (line.equals(nextLine)) {
                possible.add(i);
            }
            line = nextLine;
        }
        int i = 0;
        int mirrorIndex;
        for (int possibleHorizontalReflection : possible) {
            mirrorIndex = possibleHorizontalReflection;
            while (possibleHorizontalReflection + i < pattern.size() && possibleHorizontalReflection - i - 1 >= 0) {
                if (!pattern.get(possibleHorizontalReflection + i)
                        .equals(pattern.get(possibleHorizontalReflection - 1 - i))) {
                    mirrorIndex = -1;
                    break;
                }
                i++;

            }
            if (mirrorIndex != -1) {
                return mirrorIndex;
            }
        }
        return -1;
    }

    public static Integer checkVertical(List<String> pattern) {
        List<Integer> possible = new ArrayList<>();
        String line = "";
        for (int i = 0; i < pattern.get(0).length(); i++) {
            String nextLine = getColumnString(pattern, i);
            if (line.equals(nextLine)) {
                possible.add(i);
            }
            line = nextLine;
        }

        int mirrorIndex;
        for (int possibleVerticalReflection : possible) {
            mirrorIndex = possibleVerticalReflection;
            int i = 0;
            while (possibleVerticalReflection + i < pattern.get(0)
                    .length() && possibleVerticalReflection - i - 1 >= 0) {
                if (!getColumnString(pattern, possibleVerticalReflection + i).equals(getColumnString(pattern, possibleVerticalReflection - i - 1))) {
                    mirrorIndex = -1;
                    break;
                }
                i++;
            }
            if (mirrorIndex != -1) {
                return (mirrorIndex);
            }
        }
        return -1;

    }

    public static String getColumnString(List<String> pattern, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pattern.size(); i++) {
            sb.append(pattern.get(i).charAt(index));
        }
        return sb.toString();
    }
}
