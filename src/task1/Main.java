package task1;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int maxConsecutiveSequence(int[] arr) {
        int maxInc = 1, maxDec = 1;
        int incCount = 1, decCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                incCount++;
                decCount = 1;
            } else if (arr[i] < arr[i - 1]) {
                decCount++;
                incCount = 1;
            } else {
                incCount = decCount = 1;
            }
            maxInc = Math.max(maxInc, incCount);
            maxDec = Math.max(maxDec, decCount);
        }
        return Math.max(maxInc, maxDec);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();
        Random random = new Random();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }
        System.out.println("Сгенерированная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        int maxSequence = 0;
        for (int i = 0; i < n; i++) {
            maxSequence = Math.max(maxSequence, maxConsecutiveSequence(matrix[i]));
        }
        for (int j = 0; j < n; j++) {
            int[] column = new int[n];
            for (int i = 0; i < n; i++) {
                column[i] = matrix[i][j];
            }
            maxSequence = Math.max(maxSequence, maxConsecutiveSequence(column));
        }
        System.out.println("Максимальная длина возрастающей или убывающей последовательности: " + maxSequence);
    }
}
