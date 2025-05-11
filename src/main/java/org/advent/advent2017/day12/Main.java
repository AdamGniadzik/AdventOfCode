package org.advent.advent2017.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day12/input.txt"));

        Map<Integer, Node> map = new HashMap<>();
        while (br.ready()) {
            String[] line = br.readLine().replaceAll(",", " ").split(" ");
            int origin = Integer.parseInt(line[0]);
            Node node = map.getOrDefault(origin, new Node(origin));
            map.put(origin, node);
            for (int i = 2; i < line.length; i += 2) {
                int destination = Integer.parseInt(line[i]);
                Node destinationNode = map.getOrDefault(destination, new Node(destination));
                map.put(destination, destinationNode);
                node.connections.put(destination, destinationNode);
            }
        }
        Set<Integer> counter = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.addAll(map.get(0).connections.values());
        while (!queue.isEmpty()) {
            Node q = queue.poll();
            if (!counter.contains(q.index)) {
                counter.add(q.index);
                queue.addAll(q.connections.values());
            }
        }
        System.out.println(counter.size());
        int cycles = 0;
        counter = new HashSet<>();
        while (!map.isEmpty()) {
            queue = new LinkedList<>();
            queue.addAll(map.get(map.keySet().stream().findFirst().get()).connections.values());
            while (!queue.isEmpty()) {
                Node q = queue.poll();
                map.remove(q.index);
                if (!counter.contains(q.index)) {
                    counter.add(q.index);
                    queue.addAll(q.connections.values());
                }
            }
            cycles++;
        }
        System.out.println(cycles);

    }
    public static class Node {
        private int index;
        private Map<Integer, Node> connections = new HashMap<>();

        public Node(int index) {
            this.index = index;
        }
    }
}
