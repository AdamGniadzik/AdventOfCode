package org.advent.advent2022.day7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class ElfFile {
    Map<String, ElfFile> dirContent;
    Map<String, Integer> fileContent;

    ElfFile parent;
    String name;
    int size = 0;

    public ElfFile(String name, ElfFile parent) {
        this.name = name;
        this.fileContent = new HashMap<>();
        this.dirContent = new HashMap<>();
        this.parent = parent;
    }

    public void pushNewFile(String input) {
        if (input.startsWith("dir")) {
            String name = input.substring("dir ".length());
            this.dirContent.put(name, new ElfFile(name, this));
        } else {
            String[] splitted = input.split(" ");
            int fileSize = Integer.parseInt(splitted[0]);
            this.fileContent.put(splitted[1], fileSize);
            this.size += fileSize;
            updateParentSize(fileSize);
        }
    }

    public void updateParentSize(int fileSize) {
        if (this.parent != null) {
            this.parent.size += fileSize;
            if (this.parent.parent != null) {
                this.parent.updateParentSize(fileSize);
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        ElfFile mainDir = new ElfFile("/", null);
        ElfFile currentDir = mainDir;
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/day7/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            br.readLine(); // omit first $cd /
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("$ ls")) {
                    continue;
                } else if (line.startsWith("$ cd ")) {
                    if (line.equals("$ cd ..")) {
                        currentDir = currentDir.parent;
                    } else if (line.equals("$ cd /")) {
                        currentDir = mainDir;
                    } else {
                        currentDir = currentDir.dirContent.get(line.substring("$ cd ".length()));
                    }
                } else {
                    currentDir.pushNewFile(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//23352670
        AtomicInteger totalSizeOfDirectory = new AtomicInteger(0);
        sumFunction(mainDir, totalSizeOfDirectory);
        System.out.println(totalSizeOfDirectory.get());

        ElfFile fileToDelete = new ElfFile("", null);
        int totalDiscSpace = 70000000;
        int requiredFree = 30000000;
        fileToDelete.size = 70000000; //total available space on disc;
        int minimumToDelete = requiredFree - (totalDiscSpace - mainDir.size);
        findFileToDelete(mainDir, fileToDelete, minimumToDelete);
        System.out.println(fileToDelete.size);
    }

    public static void sumFunction(ElfFile file, AtomicInteger sum) {
        for (ElfFile innerFile : file.dirContent.values()) {
            if (innerFile.size <= 100000) {
                sum.addAndGet(innerFile.size);
            }
            sumFunction(innerFile, sum);
        }
    }

    public static void findFileToDelete(ElfFile mainDir, ElfFile toDelete, int minimumToDelete) {
        for (ElfFile innerFile : mainDir.dirContent.values()) {
            if (innerFile.size > minimumToDelete && innerFile.size < toDelete.size) {
                toDelete.size = innerFile.size;
            }
            if (innerFile.size > minimumToDelete) {
                findFileToDelete(innerFile, toDelete, minimumToDelete);
            }
        }
    }

}
