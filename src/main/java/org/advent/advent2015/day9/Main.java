package org.advent.advent2015.day9;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
      BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day9/input.txt"));
      Map<String, Node> nodes = new HashMap<>();
        while (br.ready()) {
            String[] input = br.readLine().split(" ");
            Node from = nodes.getOrDefault(input[0], new Node(input[0]));
            Node to = nodes.getOrDefault(input[2], new Node(input[2]));
            from.connection.put(to, Integer.parseInt(input[4]));
            to.connection.put(from, Integer.parseInt(input[4]));
            if (!nodes.containsKey(input[0])) {
                nodes.put(input[0], from);
            }
            if (!nodes.containsKey(input[2])) {
                nodes.put(input[2], to);
            }
        }
        String[] places = new String[nodes.keySet().size()];
        int counter = 0;
        for (String key : nodes.keySet()) {
            places[counter] = key;
            counter++;
        }

        List<List<String>> allPaths = permute(places);
        Integer minValue = Integer.MAX_VALUE;
        Integer maxValue = Integer.MIN_VALUE;
        counter = 0;
        for (List<String> path : allPaths) {
            counter++;
            int distance = 0;
            boolean completed = true;
            for (int i = 0; i < path.size() - 1; i++) {
                Node from = nodes.get(path.get(i));
                Node to = nodes.get(path.get(i + 1));
                if (from.connection.containsKey(to)) {
                    distance += from.connection.get(to);
                } else {
                    completed = false;
                    break;
                }
            }
            if (completed) {
                if (distance < minValue) {
                    minValue = distance;
                }
                if (distance > maxValue) {
                    maxValue = distance;
                }
            }
        }
        System.out.println(minValue);
        System.out.println(maxValue);
    }

    public static List<List<String>> permute(String[] nums) {
        List<List<String>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<String> result = new ArrayList<>();
        dfs(nums, results, result);
        return results;
    }

    public static void dfs(String[] nums, List<List<String>> results, List<String> result) {
        if (nums.length == result.size()) {
            List<String> temp = new ArrayList<>(result);
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

    public static class Node {
        String name;
        Map<Node, Integer> connection = new HashMap<>();

        public Node(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}

