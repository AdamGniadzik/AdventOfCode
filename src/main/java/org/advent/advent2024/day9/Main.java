package org.advent.advent2024.day9;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        firstPart();
       secondPart();

    }

    public static class Node {
        boolean isSpace;
        int value;
        int id;

        public Node(boolean isSpace, int value, int id) {
            this.isSpace = isSpace;
            this.value = value;
            this.id = id;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    public static void secondPart(){
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day9/input.txt");
            List<Node> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            boolean space = false;
            int id = 0;
            while (br.ready()) {
                int input = br.read();
                list.add(new Node(space, input - 48, id));
                if(space){
                    id++;
                }
                space = !space;

            }
            int right = list.size() - 1;
            while(right>0){
                if(list.get(right).isSpace){
                    right--;
                    continue;
                }
                int leftPointer = 0;
                int rightPointer = right;
                Node rightNode = list.get(rightPointer);
                while(leftPointer < rightPointer ){
                    Node leftNode = list.get(leftPointer);
                    if(leftNode.isSpace && rightNode.value <= leftNode.value){
                        break;
                    }
                    leftPointer++;
                }
                Node leftNode = list.get(leftPointer);
                if(leftNode.isSpace && leftPointer < rightPointer){
                    leftNode = list.get(leftPointer);
                    list.add(leftPointer, new Node(false, rightNode.value, rightNode.id));
                    leftNode.value = leftNode.value - rightNode.value;
                    list.get(rightPointer).isSpace = true;
                    list.get(rightPointer+1).isSpace = true;
                }
                right = right - 1;

            }
            int counter = 0;
            long result =0;
            for(int i =0;i<list.size();i++){
                if(list.get(i).isSpace){
                    counter+=list.get(i).value;
                }else{
                    for(int j = 0;j<list.get(i).value;j++){
                        result += (long) counter * list.get(i).id;
                        counter++;
                    }
                }
            }
            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void firstPart() {
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day9/input.txt");
            byte[] input = is.readAllBytes();
            boolean leftDiskNumber = true;
            boolean rightDiskNumber = input.length % 2 != 0;
            int left = 0;
            int right = input.length - 1;
            long result = 0;
            int currentLeftId = 0;
            int currentRightId = input.length / 2;
            int counter = 0;
            while (left <= right && left < input.length && right < input.length) {
                int leftNumber = input[left] - '0';
                if (leftDiskNumber) {
                    for (int i = 0; i < leftNumber; i++) {
                        result += (long) currentLeftId * counter;
                        counter++;
                    }
                    leftDiskNumber = false;
                    left++;
                    currentLeftId++;
                }
                int rightNumber = input[right] - '0';
                leftNumber = input[left] - '0';

                if (rightDiskNumber && left < right) {
                    while ((leftNumber > 0 && rightNumber > 0)) {
                        result += (long) counter * currentRightId;
                        counter++;
                        rightNumber--;
                        leftNumber--;
                    }
                    if (rightNumber > 0) {
                        input[right] = (byte) (rightNumber + '0');
                        left++;
                        leftDiskNumber = true;
                    } else if (leftNumber > 0) {
                        input[left] = (byte) (leftNumber + '0');
                        right = right - 2;
                        currentRightId--;
                    } else {
                        right = right - 2;
                        currentRightId--;
                        left++;
                        leftDiskNumber = true;
                    }
                }
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}