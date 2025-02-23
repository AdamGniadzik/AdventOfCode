package org.advent.advent2016.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day3/input.txt"));
        List<List<Integer>> input = new ArrayList<>();
        while (br.ready()) {
            input.add(Arrays.stream(br.readLine().replaceAll(" +", " ").substring(1).split(" ")).map(Integer::valueOf).toList());
        }
        stage1(input);
        stage2(input);

    }

    private static void stage1(List<List<Integer>> input){
        int counter = 0;
        for(List<Integer> triangle : input){
            List<Integer> sortedTriangle = new ArrayList<>(triangle).stream().sorted().toList();
            if(sortedTriangle.get(0) + sortedTriangle.get(1) > sortedTriangle.get(2)){
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void stage2(List<List<Integer>> input){
        int counter = 0;
        for(int column = 0; column < 3; column++){
            int index = 0;
            List<Integer> triangle = new ArrayList<>();
            while(index < input.size()){
                triangle.add(input.get(index).get(column));
                if(triangle.size() == 3){
                    List<Integer> sortedTriangle = new ArrayList<>(triangle).stream().sorted().toList();
                    if(sortedTriangle.get(0) + sortedTriangle.get(1) > sortedTriangle.get(2)){
                        counter++;
                    }
                    triangle.clear();
                }
                index++;
            }

        }
        System.out.println(counter);
    }
}
