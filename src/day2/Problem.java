package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Problem {
    static int part1(List<List<Integer>> list){
        int res = 0;

        for (List<Integer> ints : list) {
            if(check(ints)){
                res++;
            }
        }

        return res;
    }

    static boolean check(List<Integer> ints) {
        boolean incr = true;
        boolean decr = true;
        boolean validDiff = true;

        for (int i = 0; i < ints.size() - 1; i++) {
            int diff = ints.get(i + 1) - ints.get(i);

            if (diff < 0) incr = false;
            if (diff > 0) decr = false;

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                validDiff = false;
            }
        }

        return (incr || decr) && validDiff;
    }

    static int part2(List<List<Integer>> list){
        int res = 0;

        for (List<Integer> ints : list) {
            if(part2Helper(ints)){
                res++;
            }
        }

        return res;
    }

    static boolean part2Helper(List<Integer> ints) {
        if (check(ints)) {
            return true;
        }

        for (int i = 0; i < ints.size(); i++) {
            List<Integer> tempLevels = new ArrayList<>(ints);
            tempLevels.remove(i);

            if (check(tempLevels)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day2/input.txt"));

        List<List<Integer>> list = new ArrayList<>();
        for (String s : lines) {
            String[] split = s.split(" ");

            List<Integer> ints = new ArrayList<>();

            for (String string : split) {
                ints.add(Integer.valueOf(string));
            }

            list.add(ints);
        }

        System.out.println(part2(list));
    }
}
