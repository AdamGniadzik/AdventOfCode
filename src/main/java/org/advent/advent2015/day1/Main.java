package org.advent.advent2015.day1;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day1/input.txt"));
        int result = 0;
        int steps = 0;
        int stepsToBasement = 0;
        while (br.ready()) {
            if (br.read() == '(') {
                result++;
            } else {
                result--;
            }
            steps++;
            if(result < 0 && stepsToBasement == 0){
                stepsToBasement = steps;
            }

        }
        System.out.println(result);
        System.out.println(stepsToBasement);
    }
}
