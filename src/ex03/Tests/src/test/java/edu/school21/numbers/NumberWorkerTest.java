package edu.school21.numbers;


import static org.junit.jupiter.api.Assertions.*;

import edu.school21.numbers.NumberWorker.IllegalNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


public class NumberWorkerTest {

    private NumberWorker nw;

    @BeforeEach
    void setUp() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {49891, 49919, 49921, 49927, 49937})
    public void isPrimeForPrimes(int numbers) {
        assertTrue(nw.isPrime(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {28, 30, 32, 33})
    public void isPrimeForNotPrimes(int numbers) {
        assertFalse(nw.isPrime(numbers));
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, -1, -41})
    public void isPrimeForIncorrectNumbers(int numbers) {
        assertThrows(IllegalNumberException.class, () -> {
            nw.isPrime(numbers);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitsSum(int numbers, int result) {
        assertEquals(nw.digitsSum(numbers), result);
    }
}
