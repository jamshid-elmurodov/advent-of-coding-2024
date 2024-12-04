package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Problem {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day4/input.txt"));

        char[][] arr = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            arr[i] = lines.get(i).toCharArray();
        }

        System.out.println(part2(arr));
    }

    public static int part2(char[][] arr) {
        int c = 0;
        int rows = arr.length;
        int cols = arr[0].length;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (arr[i][j] == 'A' && check(arr, i, j)) {
                    c++;
                }
            }
        }
        return c;
    }

    static boolean check(char[][] arr, int row, int col) {
        char tl = arr[row - 1][col - 1]; // Top-left
        char tr = arr[row - 1][col + 1]; // Top-right
        char bl = arr[row + 1][col - 1]; // Bottom-left
        char br = arr[row + 1][col + 1]; // Bottom-right

        return (
                (tl == 'M' && tr == 'M' && bl == 'S' && br == 'S') || // Case 1
                        (tl == 'M' && tr == 'S' && bl == 'M' && br == 'S') || // Case 2
                        (tl == 'S' && tr == 'M' && bl == 'S' && br == 'M') || // Case 3
                        (tl == 'S' && tr == 'S' && bl == 'M' && br == 'M')    // Case 4
        );
    }


    static int[][] d = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };

    static int part1(char[][] arr) {
        int res = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[row][col] == 'X') {
                    for (int[] d : d) {
                        if (check(arr, row, col, d[0], d[1])) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    static boolean check(char[][] arr, int row, int col, int dr, int dc) {
        String word = "XMAS";

        for (int i = 0; i < word.length(); i++) {
            int r = row + dr * i;
            int c = col + dc * i;

            if (r < 0 || r >= arr.length || c < 0 || c >= arr[0].length || arr[r][c] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
