package org.advent.advent2017.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day5/input.txt"));
        List<Integer> steps = new ArrayList<>();
        while(br.ready()){
            steps.add(Integer.parseInt(br.readLine()));
        }
        stage1(new ArrayList<>(steps));
        stage2(new ArrayList<>(steps));
    }

    public static void stage1(List<Integer> input) {
        int i =0;
        int steps = 0;
        while(i < input.size()){
            int move = input.get(i);
            input.set(i, input.get(i) + 1);
            i+= move;
            steps++;
        }
        System.out.println(steps);


    }


    public static void stage2(List<Integer> input) {
        int i =0;
        int steps = 0;
        while(i < input.size()){
            int move = input.get(i);
            if(move >=3){
                input.set(i, input.get(i) - 1);
            }else{
                input.set(i, input.get(i) + 1);
            }
            i+= move;
            steps++;
        }
        System.out.println(steps);

    }



}
