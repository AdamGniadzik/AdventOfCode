package org.advent.advent2015.day11;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day11/input.txt"));
        char[] startInput = br.readLine().toCharArray();
        solve(startInput);
        increaseString(startInput, startInput.length - 1);
        solve(startInput);
    }

    public static String solve(char[] startInput){
        while(true){
            if(checkPassword(startInput)){
                String solution = new String(startInput);
                System.out.println(solution);
                return solution;
            }else{
                increaseString(startInput, startInput.length - 1);
            }
        }
    }

    public static void increaseString(char[] password, int index){
        if(password[index] == 'z'){
            password[index] = 'a';
            increaseString(password, index-1);
        }else{
            password[index] ++;
        }
    }

    public static boolean checkPassword(char[] password) {

        int patternCounter = 1;
        for (char ch : password) {
            if (ch == 'i' || ch == 'o' || ch == 'l') {
                return false;
            }
        }
        int pairs = 0;
        char pairChar = password[0];
        char currentPatternChar = password[0];
        boolean increasingPattern = false;
        for (int i = 1; i < password.length; i++) {
            if (password[i] - currentPatternChar == 1) {
                patternCounter++;
            } else {
                patternCounter = 1;
            }
            currentPatternChar = password[i];
            if (patternCounter == 3) {
                increasingPattern = true;
            }
        }
        for (int i = 1; i < password.length; i++) {
            if (password[i] == pairChar) {
                pairs++;
                if (i + 1 < password.length) {
                    pairChar = password[i + 1];
                    i++;
                }
            }else{
                pairChar = password[i];
            }
        }
        return increasingPattern && pairs >= 2;
    }


}

