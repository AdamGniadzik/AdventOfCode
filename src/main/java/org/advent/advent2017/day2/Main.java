package org.advent.advent2017.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day2/input.txt"));
        List<List<Integer>> map = new ArrayList<>();
        while(br.ready()){
            String[] input = br.readLine().split("\t");
            List<Integer> numbers = new ArrayList<>();
            for(String n : input){
                numbers.add(Integer.parseInt(n));
            }
            map.add(numbers);

        }
        stage1(map);
        stage2(map);
    }

    public static void stage1(List<List<Integer>> map) {
        int sum = 0;
        for(List<Integer> row : map){
            int max = Integer.MIN_VALUE;
            int low = Integer.MAX_VALUE;
            for(Integer num : row){
                if(num < low){
                    low = num;
                }
                if(num > max){
                    max = num;
                }
            }
            sum += max - low;
        }
        System.out.println(sum);
    }

    public static void stage2(List<List<Integer>> map) {
        int sum = 0;
        int counter =0;
        for(List<Integer> row : map){
               for(int i =0;i<row.size();i++){
                   for(int j =0;j<row.size();j++){
                       if(i != j && row.get(i) > row.get(j) && row.get(i) % row.get(j) == 0){
                           sum += row.get(i) / row.get(j);
                           counter++;
                       }
                   }
               }
        }
        System.out.println(sum);
    }



}
