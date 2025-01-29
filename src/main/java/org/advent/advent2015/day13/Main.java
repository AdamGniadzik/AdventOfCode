package org.advent.advent2015.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day13/input.txt"));
        Map<String, Integer> nameMap = new HashMap<>();
        List<String> namesList = new ArrayList<>();
        int personCounter = 0;
        List<String[]> inputList = new ArrayList<>();

        while (br.ready()) {
            String input = br.readLine().replace(".", "");
            String[] split = input.split(" ");
            inputList.add(split);
            if (!nameMap.containsKey(split[0])) {
                nameMap.put(split[0], personCounter);
                namesList.add(split[0]);
                personCounter++;
            }
            if (!nameMap.containsKey(split[10])) {
                nameMap.put(split[10], personCounter);
                namesList.add(split[10]);
                personCounter++;
            }
        }

        int[][] happinessMap = new int[namesList.size() + 1][namesList.size() + 1];
        for (String[] split : inputList) {
            happinessMap[nameMap.get(split[0])][nameMap.get(split[10])] = Integer.parseInt(split[3]) * (split[2].equals("lose") ? -1 : 1);
        }
        for (int i = 0; i < namesList.size(); i++) {
            happinessMap[happinessMap.length - 1][i] = 0;
            happinessMap[i][happinessMap.length - 1] = 0;
        }

        System.out.println(calculateStage(namesList.size(), happinessMap));
        System.out.println(calculateStage(namesList.size() + 1, happinessMap));

    }

    private static int calculateStage(int numberOfPeople, int[][] happinessMap) {
        int[] names = new int[numberOfPeople];
        for (int i = 0; i < names.length; i++) {
            names[i] = i;
        }
        List<List<Integer>> allPossibilities = permute(names);
        int finalResult = allPossibilities.stream().map(arr -> {
            int result = 0;
            for (int i = 0; i < arr.size(); i++) {
                if (i == 0) {
                    result += happinessMap[arr.get(i)][arr.get(arr.size() - 1)];
                    result += happinessMap[arr.get(i)][arr.get(1)];
                } else if (i == arr.size() - 1) {
                    result += happinessMap[arr.get(i)][arr.get(arr.size() - 2)];
                    result += happinessMap[arr.get(i)][arr.get(0)];
                } else {
                    result += happinessMap[arr.get(i)][arr.get(i - 1)];
                    result += happinessMap[arr.get(i)][arr.get(i + 1)];
                }
            }
            return result;
        }).mapToInt(Integer::valueOf).max().getAsInt();
        return finalResult;
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        dfs(nums, results, result);
        return results;
    }

    public static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result) {
        if (nums.length == result.size()) {
            List<Integer> temp = new ArrayList<>(result);
            results.add(temp);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                dfs(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

}

