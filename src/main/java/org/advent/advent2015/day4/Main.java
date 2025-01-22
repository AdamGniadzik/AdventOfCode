package org.advent.advent2015.day4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day4/input.txt"));
        String input = "";


        MessageDigest md = MessageDigest.getInstance("MD5");
        while (br.ready()) {
            input = br.readLine();
        }
        int counter = 1;
        while(true){
            String result = toHex(input +  counter, md);
            if(result.startsWith("00000")){
                System.out.println(input + counter);
                break;
            }
            counter++;
        }
        counter = 0;
        while(true){
            String result = toHex(input +  counter, md);
            if(result.startsWith("000000")){
                System.out.println(input + counter);
                break;
            }
            counter++;
        }
    }

    public static String toHex(String input, MessageDigest md) {
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }
}
