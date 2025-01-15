package org.advent.advent2024.day17;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static long registerA = 0;
    static long registerB = 0;
    static long registerC = 0;
    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day17/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            long initialA = Long.parseLong(br.readLine().substring(12));
            long initialB = Long.parseLong(br.readLine().substring(12));
            long initialC = Long.parseLong(br.readLine().substring(12));
            registerA = initialA;
            registerB = initialB;
            registerC = initialC;
            br.readLine();
            List<Integer> program = Arrays.stream(br.readLine().substring(9).split(",")).map(Integer::parseInt)
                    .toList();

            List<Integer> output = calculate(program);
            System.out.println("Stage 1: " + output);
            long baseA =  (long) ( Math.pow(8, 15));
            int powerCounter = 0;
            int incrementCounter = 0;
            while(true) {
                registerA = (long) (baseA + (incrementCounter * Math.pow(8, 15 - powerCounter)));
                registerB = initialB;
                registerC = initialC;
                output = calculate(program);
                if(compare(output, program)){
                    baseA = (long) (baseA + (incrementCounter * Math.pow(8, 15 - powerCounter)));
                    System.out.println("Stage 2: " +baseA);
                    break;
                }
                if (powerCounter < output.size() - 1 && output.get(output.size() - 1 - powerCounter).equals(program.get(output.size() - 1 - powerCounter))){
                    baseA = (long) (baseA + (incrementCounter * Math.pow(8, 15 - powerCounter)));
                    powerCounter ++;
                    incrementCounter = 0;
                }else{
                    incrementCounter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // [5, 5, 5, 5, 5, 5, 1, 5, 5]
    // [5, 5, 5, 5, 5, 5, 5, 1, 5]
    // [5, 5, 5, 5, 5, 5, 5, 6, 5]
    //  [5, 5, 5, 5, 5, 5, 5, 1, 6]
    public static boolean compare(List<Integer> list1, List<Integer> list2){
        if(list1.size() == list2.size()){
            for(int i =0;i<list1.size();i++){
                if(!list1.get(i).equals(list2.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static List<Integer> calculate(List<Integer> program){
        List<Integer> output = new ArrayList<>();
        int currentPosition = 0;
        while (currentPosition < program.size()) {
            int operation = program.get(currentPosition);
            int operand = program.get(currentPosition + 1);
            switch (operation){
                case 0:{
                    registerA = (long) (registerA / Math.pow(2, getCombo(operand)));
                    break;
                }
                case 1:{
                    registerB = registerB ^ operand;
                    break;
                }
                case 2:{
                    registerB = getCombo(operand) % 8;
                    break;
                }
                case 3:{
                    if(registerA != 0){
                        currentPosition =(int) operand;
                        continue;
                    }
                }
                case 4:{
                    registerB = registerB ^ registerC;
                    break;
                }
                case 5:{
                    output.add((int) (getCombo(operand) % 8));
                    break;
                }
                case 6:{
                    registerB = (long) (registerA / Math.pow(2, getCombo(operand)));
                    break;
                }
                case 7:{
                    registerC = (long) (registerA / Math.pow(2, getCombo(operand)));
                    break;
                }
            }
            currentPosition += 2;
        }
        return output;
    }
    public static long getCombo(int operand){
        if(operand <= 3){
            return operand;
        }
        if(operand == 4){
            return registerA;
        }
        if(operand == 5){
            return registerB;
        }
        if(operand == 6){
            return registerC;
        }
        throw new RuntimeException("Something went wrong");
    }
}