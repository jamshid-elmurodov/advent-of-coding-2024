package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Problem1 {
    static int part1(List<Integer> list1, List<Integer> list2){
        Collections.sort(list1);
        Collections.sort(list2);

        int sum = 0;

        for (int i = 0; i < list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }

        return sum;
    }

    static int part2(List<Integer> list1, List<Integer> list2){
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer i : list2) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int sum = 0;

        for (Integer i : list1) {
            sum += map.getOrDefault(i, 0) * i;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day1/input.txt"));

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (String s : lines) {
            String[] split = s.split("\\s+");

            list1.add(Integer.valueOf(split[0]));
            list2.add(Integer.valueOf(split[1]));
        }

        System.out.println(part2(list1, list2));
    }
}
