package com.example.springSecurityII.dto;

import org.springframework.stereotype.Service;

@Service
public class Search {
    // Write a Linear search to find an element in an array
    public int findElement(int element, int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public int[] moveZeros(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int minPos = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[minPos] == 0) {
                    minPos = j;
                }
            }
            int temp = numbers[minPos];
            numbers[minPos] = numbers[i];
            numbers[i] = temp;
        }
        return numbers;
    }

    public int[] moveZerosRight(int[] numbers) {
        int indexPos = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != 0) {
                numbers[indexPos] = numbers[i];
                indexPos++;
            }
        }
        while (indexPos < numbers.length) {
            numbers[indexPos++] = 0;
        }
        return numbers;
    }
}
