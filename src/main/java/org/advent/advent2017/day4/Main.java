package org.advent.advent2017.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day4/input.txt"));
        List<String> input = new ArrayList<>();
        while(br.ready()){
            input.add(br.readLine());
        }
        stage1(input);
        stage2(input);

    }

    public static void stage1(List<String> input) {
        int counter = 0;
       for(String list : input){
           String[] splited = list.split(" ");
           Set<String> set = Arrays.stream(splited).collect(Collectors.toSet());
           if(set.size() == splited.length){
               counter++;
           }
       }
        System.out.println(counter);


    }

    public static void stage2(List<String> input) {
        int counter = 0;
        for(String list : input){
            String[] splited = list.split(" ");
            Set<String> set = Arrays.stream(splited).map(s->{
                char[] arr = (s.toCharArray());
                Arrays.sort(arr);
                return new String(arr);
            }).collect(Collectors.toSet());
            if(set.size() == splited.length){
                counter++;
            }
        }
        System.out.println(counter);


    }



}
