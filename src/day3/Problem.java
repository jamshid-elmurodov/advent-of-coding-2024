package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day3/input.txt"));
        System.out.println(part2(String.join("", lines)));
    }

    static int part1(String str) {
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcher = pattern.matcher(str);

        int res = 0;

        while (matcher.find()) {
            String match = matcher.group();
            int i = match.indexOf("(");
            int j = match.indexOf(",");
            int k = match.indexOf(")");

            int x = Integer.parseInt(match.substring(i + 1, j));
            int y = Integer.parseInt(match.substring(j + 1, k));
            res += x * y;
        }

        return res;
    }

    static int part2(String str) {
        boolean isEnabled = true;
        int res = 0;

        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String match = matcher.group();

            if (match.startsWith("mul")) {
                if (isEnabled) {
                    int i = match.indexOf("(");
                    int j = match.indexOf(",");
                    int k = match.indexOf(")");

                    int x = Integer.parseInt(match.substring(i + 1, j));
                    int y = Integer.parseInt(match.substring(j + 1, k));

                    res += x * y;
                }
            } else if (match.equals("do()")) {
                isEnabled = true;
            } else if (match.equals("don't()")) {
                isEnabled = false;
            }
        }

        return res;
    }
}
