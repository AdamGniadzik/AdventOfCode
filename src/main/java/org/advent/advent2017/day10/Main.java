package org.advent.advent2017.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    static int currentPosition = 0;
    static int skipSize = 0;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day10/input.txt"));
        String input = br.readLine();
       // List<Integer> steps = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        List<Integer> circle = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            circle.add(i);
        }
        //stage1(circle, steps);
        System.out.println(circle.get(0) * circle.get(1));
        stage2(input);

    }

    public static void stage1(List<Integer> circle, List<Integer> steps) {
        for (int i = 0; i < steps.size(); i++) {
            int step = steps.get(i);
            List<Integer> reversed = new ArrayList<>(step);
            for (int j = 0; j < step; j++) {
                reversed.add(circle.get((currentPosition + j) % circle.size()));
            }
            for (int j = 0; j < step; j++) {
                circle.set((currentPosition + j) % circle.size(), reversed.get(step - 1 - j));
            }
            currentPosition += step + skipSize;
            skipSize++;
        }
    }

    public static void stage2(String input) {
        skipSize = 0;
        currentPosition = 0;
        List<Integer> circle = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            circle.add(i);
        }
        List<Integer> steps = new ArrayList<>();
        for (Character ch : input.toCharArray()) {
            steps.add((int) ch);
        }
        steps.add(17);
        steps.add(31);
        steps.add(73);
        steps.add(47);
        steps.add(23);
        for(int i =0;i<64;i++){
            stage1(circle, steps);
        }
        List<Integer> hash = new ArrayList<>();
        for(int i =0;i<16;i++){
            int tmp = circle.get(i*16);
            for(int j =1;j<16;j++){
                tmp = tmp ^ circle.get(i*16 + j);
            }
            hash.add(tmp);
        }
        for(int i =0;i<hash.size();i++){
            System.out.print(Integer.toHexString(hash.get(i)));
        }

    }

}
