package org.advent.advent2015.day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day24/input.txt"));
        List<Integer> weights = new ArrayList<>();
        int sum = 0;
        while (br.ready()) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            weights.add(num);
        }
        System.out.println(stage1(weights, sum / 3));
        System.out.println(stage2(weights, sum / 4));

    }

    public static long stage1(List<Integer> weights, Integer oneThird) {
        for (int i1 = 0; i1 < weights.size(); i1++) {
            for (int i2 = i1 + 1; i2 < weights.size(); i2++) {
                for (int i3 = i2 + 1; i3 < weights.size(); i3++) {
                    for (int i4 = i3 + 1; i4 < weights.size(); i4++) {
                        for (int i5 = i4 + 1; i5 < weights.size(); i5++) {
                            for (int i6 = i5 + 1; i6 < weights.size(); i6++) {
                                if (weights.get(i1) + weights.get(i2) + weights.get(i3) + weights.get(i4) + weights.get(i5) + weights.get(i6) == oneThird) {
                                    return (long) weights.get(i1) * weights.get(i2) * weights.get(i3) * weights.get(i4) * weights.get(i5) * weights.get(i6);
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static long stage2(List<Integer> weights, Integer oneFourth) {
        for (int i1 = 0; i1 < weights.size(); i1++) {
            for (int i2 = i1 + 1; i2 < weights.size(); i2++) {
                for (int i3 = i2 + 1; i3 < weights.size(); i3++) {
                    for (int i4 = i3 + 1; i4 < weights.size(); i4++) {
                        for (int i5 = i4 + 1; i5 < weights.size(); i5++) {
                            if (weights.get(i1) + weights.get(i2) + weights.get(i3) + weights.get(i4) + weights.get(i5) == oneFourth) {
                                return (long) weights.get(i1) * weights.get(i2) * weights.get(i3) * weights.get(i4) * weights.get(i5);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
