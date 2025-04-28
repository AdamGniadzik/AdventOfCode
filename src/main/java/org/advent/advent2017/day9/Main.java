package org.advent.advent2017.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day9/input.txt"));
        String input = br.readLine();
        stage1(input);

    }

    public static void stage1(String input) {
        Stack<Character> stack = new Stack<>();
        for (int i =0;i<input.length();i++) {
            stack.push(input.charAt(input.length()-1-i));
        }
        long sum = 0;
        int level = 0;
        int ignored = 0;
        while (!stack.isEmpty()) {
            char ch = stack.pop();
            if (ch == '{') {
                level++;
                continue;
            } else if (ch == '}') {
                sum+=level;
                level--;
                continue;
            }
            else if(ch == '!'){
                stack.pop();
            }
            else if(ch == '<'){
                while(ch != '>' && !stack.isEmpty()){
                    ch = stack.pop();
                    ignored++;
                    if(ch=='!'){
                        ignored--;
                        stack.pop();
                    }
                }
                ignored--;
            }
        }
        System.out.println(sum);
        System.out.println(ignored);
    }

}
