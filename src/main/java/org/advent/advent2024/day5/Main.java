package org.advent.advent2024.day5;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            int sum =0;
            int repairedSum = 0;
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day5/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            Map<Integer, Node> map = new HashMap<>();
            while (br.ready()) {
                String input = br.readLine();
                if (input.equals("")) {
                    break;
                }
                List<Integer> pages = Arrays.stream(input.split("\\|")).mapToInt(Integer::parseInt).boxed().toList();
                if (map.containsKey(pages.get(0))) {
                    map.get(pages.get(0)).shouldBeAfter.add(pages.get(1));
                } else {
                    Node node = new Node();
                    node.shouldBeAfter.add(pages.get(1));
                    map.put(pages.get(0), node);
                }
                if (map.containsKey(pages.get(1))) {
                    map.get(pages.get(1)).shouldBeBefore.add(pages.get(0));
                } else {
                    Node node = new Node();
                    node.shouldBeBefore.add(pages.get(0));
                    map.put(pages.get(1), node);
                }
            }

            while (br.ready()) {
                boolean failed = false;
                String input = br.readLine();
                List<Integer> numbers = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).boxed().toList();
                for (int i = 0; i < numbers.size(); i++) {
                    for (int j = i + 1; j < numbers.size(); j++) {
                        if (map.get(numbers.get(i)).shouldBeBefore.contains(numbers.get(j))) {
                            failed = true;
                            break;
                        }
                    }
                    if (failed) {
                        break;
                    }
                }

                for (int i = numbers.size()-1; i >=0; i--) {
                    for (int j = i -1; j >= 0; j--) {
                        if (map.get(numbers.get(i)).shouldBeAfter.contains(numbers.get(j))) {
                            failed = true;
                            break;
                        }
                    }
                    if (failed) {
                        break;
                    }
                }

                if (!failed) {
                    sum+= numbers.get(numbers.size()/2);
                }else{
                    LinkedList<Integer> newList = new LinkedList<>();
                    for(int i =0;i<numbers.size();i++){
                        int numberToAdd = numbers.get(i);
                        boolean hasBeenAdded = false;
                        for(int j =0;j<newList.size();j++){
                            if(map.get(newList.get(j)).shouldBeBefore.contains(numberToAdd)){
                                newList.add(j, numberToAdd);
                                hasBeenAdded = true;
                                break;
                            }
                        }
                        if(!hasBeenAdded){
                            newList.add(numberToAdd);
                        }
                    }
                    repairedSum += newList.get(newList.size() / 2);
                }

            }
            System.out.println(sum);
            System.out.println(repairedSum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Node {
        Set<Integer> shouldBeBefore = new HashSet<>();
        Set<Integer> shouldBeAfter = new HashSet<>();
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(shouldBeBefore, node.shouldBeBefore) && Objects.equals(shouldBeAfter, node.shouldBeAfter);
        }
        @Override
        public int hashCode() {
            return Objects.hash(shouldBeBefore, shouldBeAfter);
        }
    }
}