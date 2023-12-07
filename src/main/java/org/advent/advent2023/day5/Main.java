package org.advent.advent2023.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static List<Long> seedIds = new ArrayList<>();
    public static List<FarmTuple> seedSoilTuples;
    public static List<FarmTuple> soilToFertilizerTuples;
    public static List<FarmTuple> fertilizerToWaterTuples;
    public static List<FarmTuple> waterToLightTuples;
    public static List<FarmTuple> lightToTemperatureTuples;
    public static List<FarmTuple> temperatureToHumidityTuples;
    public static List<FarmTuple> humidityToLocationTuples;

    public static void main(String[] args) {


        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day5/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String seeds = br.readLine();
            seedIds.addAll(Arrays.stream(seeds.substring(7).split(" ")).map(Long::parseLong).toList());
            br.readLine();
            seedSoilTuples = readData(br);
            soilToFertilizerTuples = readData(br);
            fertilizerToWaterTuples = readData(br);
            waterToLightTuples = readData(br);
            lightToTemperatureTuples = readData(br);
            temperatureToHumidityTuples = readData(br);
            humidityToLocationTuples = readData(br);
            //Stage1
            System.out.println(seedIds.stream().map(Main::calculate).min(Long::compare).get());

            //Stage2
            List<Long> minValues = new ArrayList<>();
            for (int i = 0; i < seedIds.size(); i = i + 2) {
                Long beginning = seedIds.get(i);
                Long end = beginning + seedIds.get(i+1);
                long beginningVal;
                long endVal;
                while(beginning !=  beginning + seedIds.get(i+1) ) {
                    beginningVal = calculate(beginning);
                    endVal = calculate(end);
                    while (endVal - beginningVal != end - beginning) {
                        end = (end - beginning) / 2 + beginning;
                        endVal = calculate(end);

                    }
                    minValues.add(beginningVal);
                    if(beginning.equals(end)){
                        minValues.add(calculate(beginning + 1));
                        break;
                    }
                    beginning = end;
                    end = beginning + seedIds.get(i + 1);
                }
            }
            System.out.println(minValues.stream().min(Long::compareTo).get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Long calculate(Long seed) {
        Map<Long, List<Long>> seedToSoilMap = new HashMap<>(),
                soilToFertilizerMap = new HashMap<>(),
                fertilizerToWaterMap = new HashMap<>(),
                waterToLightMap = new HashMap<>(),
                lightToTemperatureMap = new HashMap<>(),
                temperatureToHumiditiyMap = new HashMap<>(),
                humidiityToLocationMap = new HashMap<>();

        mapValues(List.of(seed), seedSoilTuples, seedToSoilMap);
        mapValues(soilToFertilizerTuples, seedToSoilMap, soilToFertilizerMap);
        mapValues(fertilizerToWaterTuples, soilToFertilizerMap, fertilizerToWaterMap);
        mapValues(waterToLightTuples, fertilizerToWaterMap, waterToLightMap);
        mapValues(lightToTemperatureTuples, waterToLightMap, lightToTemperatureMap);
        mapValues(temperatureToHumidityTuples, lightToTemperatureMap, temperatureToHumiditiyMap);
        mapValues(humidityToLocationTuples, temperatureToHumiditiyMap, humidiityToLocationMap);
        Long value = humidiityToLocationMap.values().stream().flatMap(Collection::stream)
                .min(Long::compareTo)
                .get();
        return value;
    }

    public static List<FarmTuple> readData(BufferedReader br) throws IOException {
        br.readLine();
        String line = br.readLine();
        List<FarmTuple> inputTuples = new ArrayList<>();
        while (br.ready() && !line.equals("")) {
            inputTuples.add(new FarmTuple(line));
            line = br.readLine();

        }
        return inputTuples;
    }

    public static void mapValues(List<FarmTuple> inputTuples, Map<Long, List<Long>> sourceMap, Map<Long, List<Long>> outputMap) {
        mapValues(sourceMap.values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList()), inputTuples, outputMap);
    }

    public static void mapValues(List<Long> ids, List<FarmTuple> tuples, Map<Long, List<Long>> outputMap) {
        ids.forEach(id -> {
                    List<FarmTuple> matchingTuples = tuples.stream().filter(tuple ->
                            id >= tuple.secondVal && id <= tuple.range + tuple.secondVal
                    ).toList();
                    if (!matchingTuples.isEmpty()) {
                        matchingTuples.forEach(tuple -> {
                            addToMap(outputMap, id, tuple.firstVal + (id - tuple.secondVal));
                        });
                    } else {
                        addToMap(outputMap, id, id);
                    }
                }

        );
    }

    public static void addToMap(Map<Long, List<Long>> map, Long key, Long value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<Long> matchingValues = new ArrayList<>();
            matchingValues.add(value);
            map.put(key, matchingValues);
        }
    }

}

class FarmTuple {
    Long firstVal;
    Long secondVal;
    Long range;

    public FarmTuple(Long firstVal, Long secondVal, Long range) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
        this.range = range;
    }

    public FarmTuple(String line) {
        String[] words = line.split(" ");
        this.firstVal = Long.parseLong(words[0]);
        this.secondVal = Long.parseLong(words[1]);
        this.range = Long.parseLong(words[2]);
    }
}
