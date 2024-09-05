package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number == 1 || number == 0 || number < 0) {
            throw new IllegalNumberException("IllegalNumberException");
        }
        for (int i = 2; i * i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        String length = String.valueOf(number);
        int result = 0;
        for (int j = 0; j < length.length(); j++) {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        NumberWorker numberWorker = new NumberWorker();
        System.out.println(numberWorker.isPrime(191));
        System.out.println(numberWorker.digitsSum(479598));
    }

    public static class IllegalNumberException extends RuntimeException {

        public IllegalNumberException(String message) {
            super(message);
        }
    }
}
