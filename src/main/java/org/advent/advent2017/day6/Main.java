package org.advent.advent2017.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day6/input.txt"));
        List<Integer> steps = new ArrayList<>();
        for (String inp : br.readLine().split("\t")) {
            steps.add(Integer.parseInt(inp));
        }
        System.out.println(steps);
        stage1(new ArrayList<>(steps));
    }

    public static void stage1(List<Integer> list) {
        int[] input = list.stream().mapToInt(i -> i).toArray();
        Set<String> combinations = new HashSet<>();
        combinations.add(Arrays.toString(input));
        int cycles = 0;
        boolean stage1=true;
        boolean stage2=false;
        while(true){
            int maxIndex = 0;
            int maxValue = input[0];
            for(int i =1;i<input.length;i++){
                if(input[i] > maxValue){
                    maxIndex = i;
                    maxValue = input[i];
                }
            }
            int val = input[maxIndex];
            int index = maxIndex + 1 % input.length;
            input[maxIndex] = 0;
            for(int i =0;i<val;i++){
                input[(index+ i) % input.length]++;
            }
            cycles++;
            if(stage1 && !combinations.add(Arrays.toString(input))){
                System.out.println(cycles);
                stage1=false;
                stage2=true;
                combinations.clear();;
                combinations.add((Arrays.toString(input)));
                cycles = 0;
                continue;
            }
            if(stage2 && !combinations.add(Arrays.toString(input))){
                System.out.println(cycles);
                break;
            }
        }
    }



}
