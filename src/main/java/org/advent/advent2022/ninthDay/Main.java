package org.advent.advent2022.ninthDay;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Pointer head = new Pointer();
        Pointer tail = new Pointer();
        try {
            Set<String> positions = new HashSet<>();
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/ninthDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            positions.add(0 + " " + 0);
            while (br.ready()) {
                char direction = (char) br.read();
                br.read();
                int counter = 0;
                int numberOfSteps = Integer.parseInt(br.readLine());
                if (direction == 'D') {
                    while (counter < numberOfSteps) {
                        moveDown(head, tail);
                        counter++;
                        positions.add(tail.x + " " + tail.y);

                    }
                } else if (direction == 'U') {
                    while (counter < numberOfSteps) {
                        moveUp(head, tail);
                        counter++;
                        positions.add(tail.x + " " + tail.y);
                    }
                }
                if (direction == 'L') {
                    while (counter < numberOfSteps) {
                        moveLeft(head, tail);
                        counter++;
                        positions.add(tail.x + " " + tail.y);
                    }
                }
                if (direction == 'R') {
                    while (counter < numberOfSteps) {
                        moveRight(head, tail);
                        counter++;
                        positions.add(tail.x + " " + tail.y);
                    }
                }
            }
            System.out.println(positions.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveRight(Pointer head, Pointer tail) {
        if (head.x == tail.x + 1 && head.y == tail.y) {
            head.x++;
            tail.x++;
        } else if (head.y > tail.y && head.x - tail.x > 0) {
            head.x++;
            tail.y++;
            tail.x++;
        } else if (head.y < tail.y && head.x - tail.x > 0) {
            head.x++;
            tail.y--;
            tail.x++;
        } else {
            head.x++;
        }
    }

    public static void moveLeft(Pointer head, Pointer tail) {
        if (head.x == tail.x - 1 && head.y == tail.y) {
            head.x--;
            tail.x--;
        } else if (head.y > tail.y && head.x - tail.x < 0) {
            head.x--;
            tail.y++;
            tail.x--;
        } else if (head.y < tail.y && head.x - tail.x < 0) {
            head.x--;
            tail.y--;
            tail.x--;
        } else {
            head.x--;
        }
    }

    public static void moveUp(Pointer head, Pointer tail) {
        if (head.y == tail.y + 1 && head.x == tail.x) {
            head.y++;
            tail.y++;
        } else if (head.x > tail.x && head.y - tail.y > 0) {
            head.y++;
            tail.x++;
            tail.y++;
        } else if (head.x < tail.x && head.y - tail.y > 0) {
            head.y++;
            tail.x--;
            tail.y++;
        } else {
            head.y++;
        }
    }

    public static void moveDown(Pointer head, Pointer tail) {
        if (head.y == tail.y - 1 && head.x == tail.x) {
            head.y--;
            tail.y--;
        } else if (head.x > tail.x && head.y - tail.y < 0) {
            head.y--;
            tail.x++;
            tail.y--;
        } else if (head.x < tail.x && head.y - tail.y < 0) {
            head.y--;
            tail.x--;
            tail.y--;
        } else {
            head.y--;
        }
    }


}
