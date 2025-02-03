package org.advent.advent2015.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day17/input.txt"));
        List<Integer> containers = new ArrayList<>();
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int counter = 0;
        while (br.ready()) {
            containers.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(containers);
        int sum = 0;
        int i = 0;
        while (sum <= 150) {
            sum += containers.get(i);
            i++;
        }
        i--;

        List<Path> paths = new ArrayList<>();
        for (int c = containers.size() - 1; c >= i; c--) {
                Path path = new Path();
                path.numbers = new LinkedList<>();
                path.add(containers.get(c));
                path.index = c;
                paths.add(path);
        }

        List<Path> newPaths = new ArrayList<>();
        while (!paths.isEmpty()) {
            for(int p = 0;p<paths.size();p++){
                for (int k = paths.get(p).index - 1; k >= 0; k--) {
                    Path newPath = new Path();
                    newPath.numbers = new LinkedList<>(paths.get(p).numbers);
                    newPath.currentSum = paths.get(p).currentSum;
                    newPath.add(containers.get(k));
                    newPath.index = k;
                    if(newPath.currentSum == 150){
                        counter++;
                        sizeMap.put(newPath.numbers.size(), sizeMap.getOrDefault(newPath.numbers.size(), 0) + 1);
                    }else if(newPath.currentSum < 150){
                        newPaths.add(newPath);
                    }
                }
            }
            paths = new ArrayList<>(newPaths);
            newPaths = new ArrayList<>();
        }
        System.out.println(counter);
        System.out.println(sizeMap.get(sizeMap.keySet().stream().mapToInt(Integer::valueOf).min().getAsInt()));
    }

    static class Path {
        LinkedList<Integer> numbers;
        int index;
        int currentSum = 0;
        public void add(int number) {
            numbers.add(number);
            currentSum += number;
        }
    }
}