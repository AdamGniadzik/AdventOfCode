package org.advent.advent2017.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day1/input.txt"));
        String input = br.readLine();
        stage1(input);
        stage2(input);


    }

    public static void stage1(String input) {
        int sum = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                sum += input.charAt(i) - '0';
            }
        }
        if (input.charAt(input.length() - 1) == input.charAt(0)) {
            sum += input.charAt(0) - '0';
        }
        System.out.println(sum);
    }

    public static void stage2(String input) {
        int sum = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt((i + (input.length() / 2)) % input.length())) {
                sum += input.charAt(i) - '0';
            }
        }
        System.out.println(sum);
    }


}
