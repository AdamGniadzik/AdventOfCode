package org.advent.advent2023.day15;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day15/input.txt");
        Scanner scanner = new Scanner(is);
        long sum = 0;
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            String line = scanner.next();
            sum += getHashValue(line);

            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
    }

    public static int getHashValue(String line){
        int value = 0;
        for(int i =0;i<line.length();i++){
            char ch = line.charAt(i);
            value += ch;
            value *= 17;
            value = value % 256;
        }
        return value;
    }

}
