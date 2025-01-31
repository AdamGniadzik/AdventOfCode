package org.advent.advent2015.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day15/input.txt"));
        List<int[]> ingredientProperties = new ArrayList<>();
        while (br.ready()) {
            int[] properties = new int[5];
            String[] input = br.readLine().replace(",", "").split(" ");
            properties[0] = Integer.parseInt(input[2]);
            properties[1] = Integer.parseInt(input[4]);
            properties[2] = Integer.parseInt(input[6]);
            properties[3] = Integer.parseInt(input[8]);
            properties[4] = Integer.parseInt(input[10]);
            ingredientProperties.add(properties);
        }
        long maxResult = 0;
        long maxResultStage2 = 0;
        int max =100;
        for (int a = 1; a <= max - 3; a++) {
            for (int b = 1; b <= max - a; b++) {
                for (int c = 1; c <= max - a - b - 1; c++) {
                    int[] propValue = new int[5];
                    for (int prop = 0; prop < 5; prop++) {
                        propValue[prop] += (a * ingredientProperties.get(0)[prop]) + (b * ingredientProperties.get(1)[prop]) + (c * ingredientProperties.get(2)[prop]) + ((max - a - b - c) * ingredientProperties.get(3)[prop]);
                    }
                    int result = 1;
                    for (int prop = 0; prop < 4; prop++) {
                        result *= Math.max(0, propValue[prop]);
                    }
                    maxResult = Math.max(maxResult, result);
                    if(propValue[4] == 500){
                        maxResultStage2 = Math.max(maxResultStage2, result);
                    }
                }
            }
        }
        System.out.println(maxResult);
        System.out.println(maxResultStage2);
    }

}

//2147145000 too high