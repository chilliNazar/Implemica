package task3;

import java.math.BigInteger;

public class FactorialSum {
    public static void main(String[] args) {
        int number = 100;
        BigInteger factorial = calculateFactorial(number);
        int sumOfDigits = calculateSumOfDigits(factorial);
        System.out.println(sumOfDigits);
    }

    // Method for calculating factorial
    private static BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Method for calculating the sum of digits
    private static int calculateSumOfDigits(BigInteger number) {
        String digits = number.toString();
        int sum = 0;
        for (char digit : digits.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        return sum;
    }
}
