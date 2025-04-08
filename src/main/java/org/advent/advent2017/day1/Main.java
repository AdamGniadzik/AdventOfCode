package org.advent.advent2017.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day1/input.txt"));
        int input = Integer.parseInt(br.readLine());
        stage1(input);
        stage2(input);

    }
    public static void stage1(int input) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        int index = 0;
        for(int i =0;i<2017;i++){
            index = ((index + input) % (list.size()) )+ 1;
            list.add(index, i+1);
        }
        System.out.println(list.get(index + 1));

    }
    public static void stage2(int input) {
        int size = 1;
        int index = 0;
        int lastSaved = 0;
        for(int i =0;i<50000000;i++){
            index = ((index + input) % (size) )+ 1;
            size++;
            if(index == 1){
                lastSaved = i+1;
            }
        }
        System.out.println(lastSaved);

    }


}
