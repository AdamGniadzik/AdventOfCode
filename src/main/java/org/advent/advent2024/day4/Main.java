package org.advent.advent2024.day4;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//151310781
//110561937
public class Main {
    public static int counter = 0;
    public static int counter2 = 0;
    public static void main(String[] args) {
        long sum = 0;
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day4/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<String> input = new ArrayList<>();
            while (br.ready()) {
                input.add(br.readLine());
            }

            for(int i =0;i<input.size();i++){
                for(int j =0;j<input.get(i).length();j++){
                    if(input.get(i).charAt(j) == 'X'){
                        checkPosition(i, j, input);
                    }
                    if(input.get(i).charAt(j) == 'A'){
                        checkPosition2(i, j, input);
                    }
                }
            }
            System.out.println(counter);
            System.out.println(counter2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkPosition(int i, int j, List<String> input){
        if(j-3>=0){
            if(input.get(i).charAt(j - 1) == 'M' && input.get(i).charAt(j - 2) == 'A' && input.get(i).charAt(j - 3) == 'S'){
                counter++;
            }
        }
        if(i-3>=0){
            if(input.get(i-1).charAt(j) == 'M' && input.get(i-2).charAt(j) == 'A' && input.get(i-3).charAt(j) == 'S'){
                counter++;
            }
        }
        if(i+3 < input.size()){
            if(input.get(i+1).charAt(j) == 'M' && input.get(i+2).charAt(j) == 'A' && input.get(i+3).charAt(j) == 'S'){
                counter++;
            }
        }
        if(j+3 < input.get(i).length()){
            if(input.get(i).charAt(j+1) == 'M' && input.get(i).charAt(j+2) == 'A' && input.get(i).charAt(j+3) == 'S'){
                counter++;
            }
        }
        if(j+3 < input.get(i).length() && i +3 < input.size()){
            if(input.get(i+1).charAt(j+1) == 'M' && input.get(i+2).charAt(j+2) == 'A' && input.get(i+3).charAt(j+3) == 'S'){
                counter++;
            }
        }
        if(j-3 >= 0 && i -3 >=0){
            if(input.get(i-1).charAt(j-1) == 'M' && input.get(i-2).charAt(j-2) == 'A' && input.get(i-3).charAt(j-3) == 'S'){
                counter++;
            }
        }
        if(j+3 < input.get(i).length()  && i -3 >=0){
            if(input.get(i-1).charAt(j+1) == 'M' && input.get(i-2).charAt(j+2) == 'A' && input.get(i-3).charAt(j+3) == 'S'){
                counter++;
            }
        }
        if(j-3 >= 0 &&  i+3 < input.size()){
            if(input.get(i+1).charAt(j-1) == 'M' && input.get(i+2).charAt(j-2) == 'A' && input.get(i+3).charAt(j-3) == 'S'){
                counter++;
            }
        }
    }

    public static void checkPosition2(int i, int j, List<String> input){
        if(i-1 >= 0 && i + 1 < input.size() && j - 1 >= 0 && j+1 < input.size()){
            if(input.get(i-1).charAt(j-1) == 'M' && input.get(i+1).charAt(j+1) == 'S' || input.get(i-1).charAt(j-1) == 'S' && input.get(i+1).charAt(j+1) == 'M'){
                if(input.get(i+1).charAt(j-1) == 'M' && input.get(i-1).charAt(j+1) == 'S' || input.get(i+1).charAt(j-1) == 'S' && input.get(i-1).charAt(j+1) == 'M'){
                    counter2++;
                }
            }
        }
    }
}