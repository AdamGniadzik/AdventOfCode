package org.advent.advent2016.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {
// 249927 too high


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day16/input.txt"));
        int desiredLength = 35651584;
        String input = br.readLine();
        solution(input, 272);
        solution(input,35651584 );
    }


    public static void solution(String input, int desiredLength){
        char[] a = input.toCharArray();
        while (true) {
            char[] b = new char[a.length];
            for (int i = 0; i < a.length; i++) {
                b[a.length - i  - 1] = a[i] == '0' ? '1' : '0';
            }
            a = (new String(a) + "0" + new String(b)).toCharArray();
            if (a.length >= desiredLength) {
                String result = new String(a).substring(0, desiredLength);
                StringBuilder checksum = new StringBuilder();
                while (true) {
                    for (int i = 0; i < result.length(); i = i+2) {
                        if (i + 1 < result.length()) {
                            if (result.charAt(i) == result.charAt(i + 1)) {
                                checksum.append('1');
                            } else {
                                checksum.append('0');
                            }
                        }
                    }
                    if (checksum.length() % 2 == 0) {
                        result = checksum.toString();
                        checksum = new StringBuilder();
                    } else {
                        System.out.println(checksum);
                        break;
                    }
                }
                break;
            }
        }
    }
}
