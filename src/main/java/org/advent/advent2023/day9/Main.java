package org.advent.advent2023.day9;


import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day9/input.txt");
        Scanner scanner = new Scanner(is);

        Long sum = 0L;
        Long previousSum = 0L;
        try {
            while (scanner.hasNext()) {
                String history = scanner.nextLine();
                sum += nextHistoryValue(history);
                previousSum += previousHistoryValue(history);

            }
            System.out.println(sum);
            System.out.println(previousSum);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static Long nextHistoryValue(String history) {
        LinkedList<Long> numbers = new LinkedList<>(Arrays.stream(history.split(" ")).map(Long::parseLong)
                .collect(Collectors.toList()));
        LinkedList<LinkedList<Long>> steps = new LinkedList<>();
        steps.add(numbers);
        LinkedList<Long> currentStep = numbers;
        while (!steps.get(steps.size() - 1).stream().allMatch(i -> i.equals(0L))) {
            LinkedList<Long> newStep = new LinkedList<>();
            for (int i = 1; i < currentStep.size(); i++) {
                newStep.add(currentStep.get(i) - currentStep.get(i - 1));
            }
            steps.add(newStep);
            currentStep = steps.getLast();
        }
        currentStep.add(0L);
        for (int i = steps.size() - 2; i >= 0; i--) {
            Long newVal = (steps.get(i + 1).getLast() + steps.get(i).getLast());
            steps.get(i).add(newVal);
        }
        return numbers.getLast();
    }

    public static Long previousHistoryValue(String history) {
        LinkedList<Long> numbers = new LinkedList<>(Arrays.stream(history.split(" ")).map(Long::parseLong)
                .collect(Collectors.toList()));
        ArrayList<LinkedList<Long>> steps = new ArrayList<>();
        steps.add(numbers);
        LinkedList<Long> currentStep = numbers;
        while (!steps.get(steps.size() - 1).stream().allMatch(i -> i.equals(0L))) {
            LinkedList<Long> newStep = new LinkedList<>();
            for (int i = 1; i < currentStep.size(); i++) {
                newStep.add(currentStep.get(i) - currentStep.get(i - 1));
            }
            steps.add(newStep);
            currentStep = steps.get(steps.size() - 1);
        }
        currentStep.addFirst(0L);
        for (int i = steps.size() - 2; i >= 0; i--) {
            Long newVal = (steps.get(i).getFirst() - steps.get(i + 1).getFirst());
            steps.get(i).addFirst(newVal);
        }
        return numbers.getFirst();
    }

}