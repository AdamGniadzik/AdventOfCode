package org.advent.advent2016.day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//4793564 too high
//4793564
//4793564
//591077
//46887186

// 591077
public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day20/input.txt"));
        List<Range> input = new ArrayList<>();
        while (br.ready()) {
            String[] split = br.readLine().split("-");
            input.add(new Range(Long.parseLong(split[0]), Long.parseLong(split[1])));
        }
        stage1(input);
        stage2(input);
    }

    static void stage1(List<Range> blackList){
        List<Long> candidates = new ArrayList<>();
        candidates.add(0L);
        for(Range range : blackList){
            candidates.add(range.to + 1);
        }
        long result = Long.MAX_VALUE;
        for(int i =0;i<candidates.size();i++){
            int finalI = i;
            if(blackList.stream().noneMatch(range->candidates.get(finalI) >= range.from && candidates.get(finalI) <= range.to)){
               result = Math.min(result, candidates.get(i));
            }
        }
        System.out.println(result);
    }
    static void stage2(List<Range> blackList) {
        List<Range> allowedIps = new ArrayList<>();
        allowedIps.add(new Range(0, 4294967295L));
        for(Range blacklisted : blackList){
            List<Range> newAllowed = new ArrayList<>();
            for(Range allowed : allowedIps){
                if(blacklisted.from > allowed.to || blacklisted.to < allowed.from){
                    // No overlap between blacklist and allowed range
                    newAllowed.add(allowed);
                }
                else if(blacklisted.from > allowed.from && blacklisted.to < allowed.to){
                    // Blacklist completedy contained within allowed range
                    newAllowed.add(new Range(allowed.from, blacklisted.from - 1));
                    newAllowed.add(new Range(blacklisted.to + 1, allowed.to));
                }
                else if(allowed.from >= blacklisted.from && allowed.to > blacklisted.to){
                    //Blacklist overlaps left end of allowed range
                    newAllowed.add(new Range(blacklisted.to + 1, allowed.to));
                }
                else if(blacklisted.from > allowed.from && blacklisted.to >= allowed.to){
                    //Blacklist overlaps ight end of allowed range
                    newAllowed.add(new Range(allowed.from, blacklisted.from - 1));
                }
                else {
                    //Blacklist completely overlaps allowed range
                }
            }
            allowedIps = newAllowed;
        }
        long result = 0;
        for(Range allowed : allowedIps){
            result += allowed.to - allowed.from;
            if(allowed.to - allowed.from == 0){
                result++;
            }
        }
        System.out.println(result);
    }


    public record Range(long from, long to) {
    }
}
