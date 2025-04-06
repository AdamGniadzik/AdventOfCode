package org.advent.advent2016.day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<String, Long> registers = new HashMap<>();


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day23/input.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day23/input2.txt"));
        List<String[]> commands = new ArrayList<>();
        List<String[]> commands2 = new ArrayList<>();
        while (br.ready()) {
            commands.add(br.readLine().split(" "));
        }
        br.close();;
        while (br2.ready()) {
            commands2.add(br2.readLine().split(" "));
        }
        br2.close();;

        stage1(commands);
        stage2(commands2);


    }


    public static void stage1(List<String[]> commands){
        registers.put("a", 7L);
        registers.put("b", 0L);
        registers.put("c", 0L);
        registers.put("d", 0L);
        compute(commands);
        System.out.println(registers);

    }
    public static void stage2(List<String[]> commands){
        registers.put("a", 12L);
        registers.put("b", 0L);
        registers.put("c", 0L);
        registers.put("d", 0L);
        registers.put("g", 0L);
        compute(commands);
        System.out.println(registers);
    }
    public static void compute(List<String[]> commands){
        for(int i =0;i<commands.size();i++){
            String[] command = commands.get(i);
            switch (command[0]){
                case "addmul":{
                    String target = command[1];
                    Long resultToAdd = registers.get(command[2]) * registers.get(command[3]);
                    registers.put(target, registers.get(target) + resultToAdd);
                    i += 4;
                }
                case "cpy":{
                    Long number;
                    try{
                        number = Long.parseLong(command[1]);
                    }catch (Exception e){
                        number = registers.get(command[1]);
                    }
                    if(!registers.containsKey(command[2])){
                        continue;
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
                    long number;
                    try{
                        number = Long.parseLong(command[1]);
                    }catch (Exception e){
                        number = registers.get(command[1]);
                    }
                    if(number != 0 ){
                        long jump;
                        try{
                            jump = Long.parseLong(command[2]);
                        }catch (Exception e){
                            jump = registers.get(command[2]);
                        }
                        i = (int) (i + jump - 1);
                    }
                    break;
                }
                case "tgl":{
                    StringBuilder newCommand = new StringBuilder();
                    Long number;
                    try{
                        number = Long.parseLong(command[1]);
                    }catch (Exception e){
                        number = registers.get(command[1]);
                    }
                    if(i+number >= commands.size() || i + number < 0){
                        continue;
                    }
                    String[] selectedCommand = commands.get((int) (i + number));
                    if(selectedCommand.length == 2){
                        if(selectedCommand[0].equals("inc")){
                            newCommand.append("dec");
                        }else{
                            newCommand.append("inc");
                        }
                        newCommand.append(" ").append(selectedCommand[1]);
                    }else{
                        if(selectedCommand[0].equals("jnz")){
                            newCommand.append("cpy");
                            newCommand.append(" ").append(selectedCommand[1]).append(" ").append(selectedCommand[2]);
                        }else{
                            newCommand.append("jnz").append(" ").append(selectedCommand[1]).append(" ").append(selectedCommand[2]);
                        }
                    }
                    commands.set((int) (i+number), newCommand.toString().split(" "));
                }
            }
        }
    }


}
