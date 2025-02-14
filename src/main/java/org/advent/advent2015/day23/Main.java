package org.advent.advent2015.day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day23/input.txt"));
        List<String> input = new ArrayList<>();
        while (br.ready()) {
            input.add(br.readLine());
        }
        solution(0, 0, input);
        solution(1, 0, input);

    }

    public static void solution(int A, int B, List<String> input){
        for (int i = 0; i < input.size(); i++) {
            String[] operation = input.get(i).split(" ");
            switch (operation[0]) {
                case "hlf" -> {
                    if(operation[1].equals("a")){
                        A = A/2;
                    }else{
                        B = B/2;
                    }
                }
                case "tpl" -> {
                    if(operation[1].equals("a")){
                        A = A * 3;
                    }else{
                        B = B * 3;
                    }
                }
                case "inc" -> {
                    if(operation[1].equals("a")){
                        A++;
                    }else{
                        B++;
                    }
                }
                case "jmp" -> i = i - 1 + Integer.parseInt(operation[1]);

                case "jie" -> {
                    if(operation[1].charAt(0) == 'a' && A % 2 == 0){
                        i = i - 1 + Integer.parseInt(operation[2]);
                    }else if(operation[1].charAt(0) == 'b' && B % 2 == 0){
                        i = i - 1 + Integer.parseInt(operation[2]);
                    }
                }
                case "jio" -> {
                    if(operation[1].charAt(0) == 'a' && A == 1){
                        i = i - 1 + Integer.parseInt(operation[2]);
                    }else if(operation[1].charAt(0) == 'b' && B == 1){
                        i = i - 1 + Integer.parseInt(operation[2]);
                    }
                }
            }
        }
        System.out.println(B);
    }

}
