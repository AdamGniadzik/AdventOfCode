package org.advent.advent2024.day3;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//151310781
//110561937
public class Main {
    public static void main(String[] args) {
        long sum = 0;
        try {
            InputStream is = Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day3/input.txt");
            String input = new String(is.readAllBytes());
            input = input.replaceAll("\s", "");

            Pattern generalPattern = Pattern.compile("(?<=do\\(\\))(.+?)(?<=don't\\(\\))|(?<=^)(.+?)(?<=don't\\(\\))|(?<=do\\(\\))(.+?)(?<=$)", Pattern.CASE_INSENSITIVE);
            Matcher generalMatcher = generalPattern.matcher(input);
            while (generalMatcher.find()) {
                String filtered = generalMatcher.group();
                Pattern mulPatter = Pattern.compile("mul\\(\\d+,\\d+\\)");
                Matcher mulMatcher = mulPatter.matcher(filtered);
                while (mulMatcher.find()) {
                    String expr = mulMatcher.group();
                    int indexOf = expr.indexOf(",");
                    Integer firstNumber = Integer.parseInt(expr.substring(4, indexOf));
                    Integer secondNumber = Integer.parseInt(expr.substring(indexOf + 1, expr.length() - 1));
                    sum += (long) firstNumber * secondNumber;
                }
            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


