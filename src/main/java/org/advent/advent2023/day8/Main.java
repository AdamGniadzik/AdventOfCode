package org.advent.advent2023.day8;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {


        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day8/input.txt");
            Map<String, Node> map = new HashMap<>();
            Scanner scanner = new Scanner(is);
            String route = scanner.nextLine();

            scanner.nextLine();
            while (scanner.hasNext()) {
                String singleRoute = scanner.nextLine();
                String origin = singleRoute.substring(0, 3);
                String left = singleRoute.substring(7, 10);
                String right = singleRoute.substring(12, 15);
                map.put(origin, new Node(origin, left, right));
            }
            //Stage 1
//            Node current = map.get("AAA");
//            int counter = 0;
//            while(!current.origin.equals("ZZZ")){
//                if(route.charAt(counter % route.length()) == 'L' ){
//                    current = map.get(current.left);
//                    counter++;
//                }else{
//                    current = map.get(current.right);
//                    counter++;
//                }
//            }
//            System.out.println(counter);

            //Stage 2
            List<Node> origins = map.values().stream().filter(Node::isStartingNode).collect(Collectors.toList());
            origins.forEach(node -> {
                int counter = 0;
                Node current = node;
                while (!node.visitedZNodes.contains(current.origin)) {
                    if (route.charAt(counter % route.length()) == 'L') {
                        current = map.get(current.left);
                        counter++;
                    } else {
                        current = map.get(current.right);
                        counter++;
                    }
                    if(current.origin.charAt(2) == 'Z'){
                        node.visitedZNodes.add(current.origin);
                    }
                }
                node.counterToBack = counter;
                System.out.println(counter);
            });
            List<Long> counters = origins.stream().map(origin -> Long.valueOf(origin.counterToBack)).toList();
            Long requiredSteps = lcm(counters.get(0), counters.get(1));
            for(int i =2; i<counters.size();i++){
                requiredSteps = lcm(requiredSteps, counters.get(i));
                System.out.println(requiredSteps);
            }
            System.out.println(requiredSteps);



        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    public static long gcd (long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            long absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }
    public static long lcm(long number1, long number2){
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            long gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }


}

class Node {
    String origin;
    String left;
    String right;
    boolean isStartingNode = false;
    Set<String> visitedZNodes = null;
    int counterToBack = 0;

    public Node(String origin, String left, String right) {
        this.origin = origin;
        this.left = left;
        this.right = right;
        if (origin.charAt(2) == 'A') {
            isStartingNode = true;
            visitedZNodes = new HashSet<>();

        }
    }

    public boolean isStartingNode() {
        return isStartingNode;
    }


}
