package org.advent.advent2015.day2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day2/input.txt"));
        int ribbon = 0;
        int surfaceResult = 0;
        while (br.ready()) {
            List<Integer> measurements = Arrays.stream(br.readLine().split("x")).map(Integer::parseInt).sorted().toList();
            int sideA = measurements.get(0) * measurements.get(1);
            int sideB = measurements.get(0) * measurements.get(2);
            int sideC = measurements.get(1) * measurements.get(2);
            surfaceResult += (2 * sideA + 2 * sideB + 2 * sideC) + sideA;
            ribbon += measurements.get(0) * 2 + measurements.get(1) * 2  + (measurements.get(0) * measurements.get(1) * measurements.get(2));
        }
        System.out.println(surfaceResult);
        System.out.println(ribbon);
    }
}
