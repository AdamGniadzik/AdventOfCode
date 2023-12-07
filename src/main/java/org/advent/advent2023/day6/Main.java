package org.advent.advent2023.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {


        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day6/input.txt");
            Scanner scanner = new Scanner(is);
            List<Long> times = new ArrayList<>();
            List<Long> distances = new ArrayList<>();
            scanner.next();
            while (scanner.hasNext()) {
                try {
                    times.add(scanner.nextLong());
                } catch (Exception e) {
                    scanner.next();
                    while (scanner.hasNext()) {
                        distances.add(scanner.nextLong());
                    }
                }
            }
            bruteSolution(times, distances);
            System.out.println();
            beautifulSolution(times, distances);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void beautifulSolution(List<Long> times, List<Long> distances){
        long time = System.nanoTime();
        BigInteger wins = BigInteger.ONE;
        for (int i = 0; i < times.size(); i++) {
            BigDecimal t = BigDecimal.valueOf(times.get(i));
            BigDecimal d = BigDecimal.valueOf(distances.get(i));
            BigDecimal deltaSqrt = t
                    .multiply(t).subtract(BigDecimal.valueOf(4).multiply(d)).sqrt(MathContext.DECIMAL128);
            BigDecimal b1 = t.subtract(deltaSqrt).divide(BigDecimal.TWO);
            BigDecimal b2 = t.add(deltaSqrt).divide(BigDecimal.TWO);
            BigInteger possibleWinds = b2.toBigInteger().subtract(b1.toBigInteger());
            wins = wins.multiply(b2.remainder(BigDecimal.ONE)
                    .compareTo(BigDecimal.ZERO) == 0 ? possibleWinds.subtract(BigInteger.ONE) : possibleWinds);
        }
        System.out.println(wins);
        System.out.println(System.nanoTime() - time);
    }
    public static void bruteSolution(List<Long> times, List<Long> distances) {
        long time = System.nanoTime();
        List<Long> possibleWinsArr = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            boolean border = false;
            long possibleWins = 0;
            for (int j = 1; j < times.get(i); j++) {
                long possibleDistance = j * (times.get(i) - j);
                if (possibleDistance > distances.get(i)) {
                    possibleWins++;
                    border = true;
                } else if (border) {
                    break;
                }
            }
            possibleWinsArr.add(possibleWins);
        }
        Long result = 1L;

        for (Long i : possibleWinsArr) {
            result *= i;
        }
        System.out.println(result);
        System.out.println(System.nanoTime() - time);
    }


}
