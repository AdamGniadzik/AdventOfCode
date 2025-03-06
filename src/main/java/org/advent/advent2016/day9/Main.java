package org.advent.advent2016.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day9/input.txt"));
        String input = br.readLine();
        System.out.println(stage1(input));
        System.out.println(stage2(input));


    }


    private static int stage1(String input) {
        int startBracket = input.indexOf('(');
        int decompressedSize = 0;
        int endBracket = 0;
        while (startBracket != -1) {
            decompressedSize += startBracket - endBracket;
            endBracket = input.indexOf(')', startBracket);
            String[] markers = input.substring(startBracket + 1, endBracket).split("x");
            int howManyChars = Integer.parseInt(markers[0]);
            int multiplier = Integer.parseInt(markers[1]);
            decompressedSize += howManyChars * multiplier;
            startBracket = input.indexOf('(', endBracket + howManyChars);
            endBracket += Integer.parseInt(markers[0]) + 1;
        }
        decompressedSize += input.length() - endBracket;
        return decompressedSize;
    }

    private static long stage2(String input) {
        int startBracket = input.indexOf('(');
        long decompressedSize = 0;
        int endBracket = 0;
        while (startBracket != -1) {
            decompressedSize += startBracket - endBracket;
            endBracket = input.indexOf(')', startBracket);
            String[] markers = input.substring(startBracket + 1, endBracket).split("x");
            int howManyChars = Integer.parseInt(markers[0]);
            int multiplier = Integer.parseInt(markers[1]);
            decompressedSize += stage2(input.substring(endBracket + 1, endBracket + 1 + howManyChars)) * multiplier;
            startBracket = input.indexOf('(', endBracket + howManyChars);
            endBracket += Integer.parseInt(markers[0]) + 1;
        }
        decompressedSize += input.length() - endBracket;
        return decompressedSize;
    }
}
