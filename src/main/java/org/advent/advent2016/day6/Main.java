package org.advent.advent2016.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day6/input.txt"));
        List<String> input = new ArrayList<>();

        while (br.ready()) {
            input.add(br.readLine());
        }
        solution(input);

    }

    public static void solution(List<String> inputList) {
        StringBuilder stage1 = new StringBuilder();
        StringBuilder stage2 = new StringBuilder();
        for(int i =0;i<inputList.get(0).length(); i++){
            Map<Character, Integer> map = new HashMap<>();
            for(int j =0;j<inputList.size();j++){
                map.put(inputList.get(j).charAt(i), map.getOrDefault(inputList.get(j).charAt(i), 0) + 1);
            }
            stage1.append(map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
            stage2.append(map.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey());
        }
        System.out.println(stage1);
        System.out.println(stage2);
    }
}
