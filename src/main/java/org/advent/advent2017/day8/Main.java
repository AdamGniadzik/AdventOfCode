package org.advent.advent2017.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day8/input.txt"));
        List<String[]> commands = new ArrayList<>();
        while(br.ready()){
            commands.add(br.readLine().split(" "));
        }

        stage1(commands);


    }

    public static void stage1(List<String[]> commands){
        int max = 0;
        Map<String, Integer> registers= new HashMap<>();
        for(String[] command : commands){
            String register = command[0];
            registers.putIfAbsent(register, 0);
            int registerCurrentValue = registers.get(register);
            int multiplier = command[1].equals("inc") ? 1 : -1;
            int operand = Integer.parseInt(command[2]);
            String conditionRegister = command[4];
            int conditionRegisterValue = registers.getOrDefault(conditionRegister, 0 );
            String condition = command[5];
            int conditionOperand = Integer.parseInt(command[6]);
            boolean conditionResult = false;
            if(condition.equals(">")){
                conditionResult = conditionRegisterValue > conditionOperand;
            }else if(condition.equals("<")){
                conditionResult = conditionRegisterValue < conditionOperand;
            }
            else if(condition.equals("<=")){
                conditionResult = conditionRegisterValue <= conditionOperand;
            }
            else if(condition.equals(">=")){
                conditionResult = conditionRegisterValue >= conditionOperand;
            }
            else if(condition.equals("==")){
                conditionResult = conditionRegisterValue == conditionOperand;
            }
            else if(condition.equals("!=")){
                conditionResult = conditionRegisterValue != conditionOperand;
            }
            if(conditionResult){

                registers.put(register, registerCurrentValue + (operand * multiplier));
                max = Math.max(max, registers.get(register));
            }


        }
        System.out.println(registers.values().stream().max(Integer::compare).get());
        System.out.println(max);
    }

}
