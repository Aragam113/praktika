package task5;

import java.util.Scanner;

public class TextProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String input = scanner.nextLine();
        String processedText = input.replaceAll("[^a-zA-Zа-яА-Я ]", "").replaceAll("\\s+", " ");
        System.out.println("Результат:");
        System.out.println(processedText.trim());
    }
}
