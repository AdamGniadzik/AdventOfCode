package org.advent.advent2016.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day18/input.txt"));
        String input = br.readLine();
        stage1(input, 40);
        stage1(input, 400000);

    }

    static void stage1(String input, int rows) {
        int traps = 0;
        for(char ch : input.toCharArray()){
            if(ch == '.'){
                traps++;
            }
        }
        for (int steps = 0; steps < rows - 1; steps++) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char newChar;
                if (i == 0) {
                    newChar = solve('.', input.charAt(i), input.charAt(i + 1));
                } else if (i == input.length() - 1) {
                    newChar = solve(input.charAt(i-1), input.charAt(i), '.');
                } else {
                    newChar = solve(input.charAt(i - 1), input.charAt(i), input.charAt(i + 1));
                }
                str.append(newChar);
                if (newChar == '.') {
                    traps++;
                }
            }
            input = str.toString();
        }
        System.out.println(traps);
    }

    static char solve(char left, char center, char right) {
        if (left == '^' && center == '^' && right == '.') {
            return '^';
        }
        if (center == '^' && right == '^' && left == '.') {
            return '^';

        }
        if (left == '^' && right == '.' && center == '.') {
            return '^';

        }
        if (left == '.' && right == '^' && center == '.') {
            return '^';

        }
        return '.';
    }

}
