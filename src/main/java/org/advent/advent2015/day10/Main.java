package org.advent.advent2015.day10;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day10/input.txt"));
        String startInput = br.readLine();
        solve(startInput, 40);
        solve(startInput, 50);
    }

    public static void solve(String startInput, Integer times) {
        for (int i = 0; i < times; i++) {
            StringBuilder str = new StringBuilder();
            char[] input = startInput.toCharArray();
            char currentDigit = input[0];
            int counter = 0;
            int pointer = 0;
            while (pointer < input.length) {
                if (input[pointer] == currentDigit) {
                    counter++;
                    pointer++;
                } else {
                    str.append(counter).append(currentDigit);
                    currentDigit = input[pointer];
                    counter = 0;
                }
            }
            str.append(counter).append(currentDigit);
            startInput = str.toString();
        }
        System.out.println(startInput.length());
    }
}

