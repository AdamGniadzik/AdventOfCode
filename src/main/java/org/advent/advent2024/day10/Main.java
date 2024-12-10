package org.advent.advent2024.day10;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day10/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Integer>> input = new ArrayList<>();
            int row = 0;
            int column = 0;
            List<Node> starts = new ArrayList<>();

            List<Integer> current = new ArrayList<>();
            while (br.ready()) {
                int c = (br.read() - '0');
                if (c == '\n' - '0') {
                    continue;
                }
                if (c == '\r' - '0') {
                    input.add(current);
                    current = new ArrayList<>();
                    row++;
                    column = 0;
                    continue;
                }
                if (c == 0) {
                    starts.add(new Node(0, column, row));
                }
                current.add(c);
                column++;
            }
            input.add(current);

            int result = 0 ;
            int result2 = 0;
            for(Node node: starts){
                search(input, node, node);
                result += node.ends.size();
                result2 += node.score;
            }

            System.out.println(result);
            System.out.println(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void search(List<List<Integer>> input, Node current, Node root) {
        int x = current.x;
        int y = current.y;
        if(current.value == 9){
            root.score ++;
            root.ends.add(new Pair(x, y));
        }

        if (x - 1 >= 0 && input.get(y).get(x-1) - current.value == 1) {
            search(input, new Node(input.get(y).get(x-1), x - 1, y), root);
        }
        if (y - 1 >= 0 && input.get(y-1).get(x) - current.value == 1) {
            search(input, new Node(input.get(y-1).get(x), x, y - 1), root);
        }
        if (x + 1 < input.get(0).size() && input.get(y).get(x+1) - current.value == 1) {
            search(input, new Node(input.get(y).get(x + 1), x + 1, y), root);
        }
        if (y + 1 < input.size() && input.get(y + 1).get(x) - current.value == 1) {
            search(input, new Node(input.get(y + 1).get(x), x, y + 1), root);
        }
    }

    public static class Node {
        int value;
        int x;
        int y;
        int score = 0;
        Set<Pair> ends = new HashSet<>();

        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

    }

    public static class Pair {
        int row;
        int column;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row && column == pair.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

}