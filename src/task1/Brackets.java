package task1;
import java.util.Scanner;

public class Brackets {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of pairs of brackets (N):");
        int n = scanner.nextInt();

        System.out.println("All valid bracket expressions:");
        generateBrackets("", n, n);
    }

    // Method to generate valid bracket expressions
    public static void generateBrackets(String current, int open, int close) {
        // If all brackets are used, print the result
        if (open == 0 && close == 0) {
            System.out.println(current);
            return;
        }

        // Add an opening bracket if it can be added
        if (open > 0) {
            generateBrackets(current + "(", open - 1, close);
        }

        // Add a closing bracket if it can be added (provided there are more closing brackets available than opening ones)
        if (close > open) {
            generateBrackets(current + ")", open, close - 1);
        }
    }
}
