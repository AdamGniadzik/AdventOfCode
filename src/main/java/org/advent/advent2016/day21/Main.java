package org.advent.advent2016.day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day21/input.txt"));
        List<String> operations = new ArrayList<>();
        while (br.ready()) {
            operations.add(br.readLine());
        }
        stage2(operations);
    }

    static void stage2(List<String> operations) {
        List<Character> password = new ArrayList<>(List.of('f', 'b', 'g', 'd', 'c', 'e', 'a', 'h'));
        for (int k = operations.size() - 1; k >= 0; k--) {
            String operation = operations.get(k);
            String[] operationWords = operation.split(" ");
            if (operationWords[0].equals("swap") && operationWords[1].equals("position")) {
                int x = Integer.parseInt(operationWords[5]);
                int y = Integer.parseInt(operationWords[2]);
                char tmp = password.get(x);
                password.set(x, password.get(y));
                password.set(y, tmp);
            } else if (operationWords[0].equals("swap") && operationWords[1].equals("letter")) {
                char x = operationWords[5].charAt(0);
                char y = operationWords[2].charAt(0);
                for (int i = 0; i < password.size(); i++) {
                    if (password.get(i) == x) {
                        password.set(i, y);
                    } else if (password.get(i) == y) {
                        password.set(i, x);
                    }
                }
            } else if (operationWords[0].equals("rotate") && operationWords[1].equals("based")) {
                Map<Integer, Integer> map = Map.of(1,0,3,1,5,2,7,3,2,4,4,5,6,6,0,7);
//                0->1
//                1->3
//                2->5
//                3->7
//                4->10 -> 2
//                5->12 -> 4
//                6->14 -> 6
//                7->16 -> 0
                List<Character> newPassword = new ArrayList<>();
                int index = password.indexOf(operationWords[6].charAt(0));
                if (index != -1) {
                    int destination = map.get(index);
                    if(destination < index){
                        for (int i = index - destination; i < password.size(); i++) {
                            newPassword.add(password.get(i % password.size()));
                        }
                        for (int i = 0; i < index - destination; i++) {
                            newPassword.add(password.get(i));
                        }
                    }else{
                        for (int i = password.size() - (destination - index); i < password.size(); i++) {
                            newPassword.add(password.get(i % password.size()));
                        }
                        for (int i = 0; i < password.size() - (destination - index); i++) {
                            newPassword.add(password.get(i));
                        }
                    }
                    password = newPassword;
                }
            } else if (operationWords[0].equals("rotate")) {
                List<Character> newPassword = new ArrayList<>();
                int count = Integer.parseInt(operationWords[2]);
                if (operationWords[1].equals("right")) {
                    for (int i = count; i < password.size(); i++) {
                        newPassword.add(password.get(i % password.size()));
                    }
                    for (int i = 0; i < count; i++) {
                        newPassword.add(password.get(i));
                    }
                } else if (operationWords[1].equals("left")) {
                    for (int i = password.size() - count; i < password.size(); i++) {
                        newPassword.add(password.get(i % password.size()));
                    }
                    for (int i = 0; i < password.size() - count; i++) {
                        newPassword.add(password.get(i));
                    }
                }
                password = newPassword;
            } else if (operationWords[0].equals("reverse")) {
                List<Character> newPassword = new ArrayList<>();
                int x = Integer.parseInt(operationWords[2]);
                int y = Integer.parseInt(operationWords[4]);
                int count = 0;
                for (int i = 0; i < password.size(); i++) {
                    if (i >= x && i <= y) {
                        newPassword.add(password.get((y - count)));
                        count++;
                    } else {
                        newPassword.add(password.get(i));
                    }
                }
                password = newPassword;
            } else if (operationWords[0].equals("move")) {
                int x = Integer.parseInt(operationWords[5]);
                int y = Integer.parseInt(operationWords[2]);
                char tmp = password.get(x);
                password.remove(x);
                password.add(y, tmp);
            }

            System.out.println(operation + "   " + password);
        }
    }

    static void stage1(List<String> operations) {
        List<Character> password = new ArrayList<>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
        //List<Character> password = new ArrayList<>(List.of('a', 'b', 'c', 'd', 'e'));
        for (String operation : operations) {
            String[] operationWords = operation.split(" ");
            if (operationWords[0].equals("swap") && operationWords[1].equals("position")) {
                int x = Integer.parseInt(operationWords[2]);
                int y = Integer.parseInt(operationWords[5]);
                char tmp = password.get(x);
                password.set(x, password.get(y));
                password.set(y, tmp);
            } else if (operationWords[0].equals("swap") && operationWords[1].equals("letter")) {
                char x = operationWords[2].charAt(0);
                char y = operationWords[5].charAt(0);
                for (int i = 0; i < password.size(); i++) {
                    if (password.get(i) == x) {
                        password.set(i, y);
                    } else if (password.get(i) == y) {
                        password.set(i, x);
                    }
                }
            } else if (operationWords[0].equals("rotate") && operationWords[1].equals("based")) {
                List<Character> newPassword = new ArrayList<>();
                int index = password.indexOf(operationWords[6].charAt(0));
                if (index != -1) {
                    int count = index + 1;
                    if (index >= 4) {
                        count++;
                    }
                    count = count % password.size();
                    for (int i = password.size() - count; i < password.size(); i++) {
                        newPassword.add(password.get(i % password.size()));
                    }
                    for (int i = 0; i < password.size() - count; i++) {
                        newPassword.add(password.get(i));
                    }
                    password = newPassword;
                }
            } else if (operationWords[0].equals("rotate")) {
                List<Character> newPassword = new ArrayList<>();
                int count = Integer.parseInt(operationWords[2]);
                if (operationWords[1].equals("right")) {
                    for (int i = password.size() - count; i < password.size(); i++) {
                        newPassword.add(password.get(i % password.size()));
                    }
                    for (int i = 0; i < password.size() - count; i++) {
                        newPassword.add(password.get(i));
                    }
                } else if (operationWords[1].equals("left")) {
                    for (int i = count; i < password.size(); i++) {
                        newPassword.add(password.get(i % password.size()));
                    }
                    for (int i = 0; i < count; i++) {
                        newPassword.add(password.get(i));
                    }
                }
                password = newPassword;
            } else if (operationWords[0].equals("reverse")) {
                List<Character> newPassword = new ArrayList<>();
                int x = Integer.parseInt(operationWords[2]);
                int y = Integer.parseInt(operationWords[4]);
                int count = 0;
                for (int i = 0; i < password.size(); i++) {
                    if (i >= x && i <= y) {
                        newPassword.add(password.get((y - count)));
                        count++;
                    } else {
                        newPassword.add(password.get(i));
                    }
                }
                password = newPassword;
            } else if (operationWords[0].equals("move")) {
                int x = Integer.parseInt(operationWords[2]);
                int y = Integer.parseInt(operationWords[5]);
                char tmp = password.get(x);
                password.remove(x);
                password.add(y, tmp);
            }

            System.out.println(operation + "   " + password);
        }
    }

}
