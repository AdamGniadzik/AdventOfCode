package org.advent.advent2016.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    static Map<String, Integer> registers = new HashMap<>();


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day12/input.txt"));
        List<String[]> commands = new ArrayList<>();
        while (br.ready()) {
            commands.add(br.readLine().split(" "));
        }

        stage1(commands);
        stage2(commands);


    }


    public static void stage1(List<String[]> commands){
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);
        compute(commands);
        System.out.println(registers);

    }
    public static void stage2(List<String[]> commands){
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1);
        registers.put("d", 0);
        compute(commands);
        System.out.println(registers);

    }
    public static void compute(List<String[]> commands){
        for(int i =0;i<commands.size();i++){
            String[] command = commands.get(i);
            switch (command[0]){
                case "cpy":{
                    Integer number;
                    try{
                        number = Integer.parseInt(command[1]);
                    }catch (Exception e){
                        number = registers.get(command[1]);
                    }
                    registers.put(command[2], number);
                    break;
                }
                case "inc":{
                    registers.put(command[1], registers.get(command[1]) + 1);
                    break;
                }
                case "dec":{
                    registers.put(command[1], registers.get(command[1]) - 1);
                    break;
                }
                case "jnz":{
                    int number;
                    try{
                        number = Integer.parseInt(command[1]);
                    }catch (Exception e){
                        number = registers.get(command[1]);
                    }
                    if(number != 0 ){
                        int jump = Integer.parseInt(command[2]);
                        i = i + jump - 1;
                    }
                    break;
                }
            }
        }
    }


}
