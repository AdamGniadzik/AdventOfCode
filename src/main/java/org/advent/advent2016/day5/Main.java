package org.advent.advent2016.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day5/input.txt"));
        String input = "";


        MessageDigest md = MessageDigest.getInstance("MD5");
        while (br.ready()) {
            input = br.readLine();
        }
        int counter = 1;
        StringBuilder password = new StringBuilder();
        while(true){
            String result = toHex(input +  counter, md);
            if(result.startsWith("00000")){
                password.append(result.charAt(5));
                if(password.length() == 8){
                    break;
                }
            }
            counter++;
        }
        System.out.println(password);
        char[] passwordStage2 = new char[8];
        int passwordCounter = 0;
        counter = 0;
        while(true){
            String result = toHex(input +  counter, md);
            if(result.startsWith("00000")){
                if(result.charAt(5) >= '0' && result.charAt(5) <= '7'){
                    if(passwordStage2[result.charAt(5) - '0'] == 0){
                        passwordCounter++;
                        passwordStage2[result.charAt(5) - '0'] = result.charAt(6);
                    }
                }
                if(passwordCounter == 8){
                    break;
                }
            }
            counter++;
        }
        System.out.println(new String(passwordStage2));
    }

    public static String toHex(String input, MessageDigest md) {
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }
}
