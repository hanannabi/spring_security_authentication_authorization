package com.example.springSecurityII.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static com.example.springSecurityII.dto.SearchFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@AllArgsConstructor
class SearchTest {

    @Autowired
    private Search search;//TDD ==> Test Driven Development

    @Test
    void shouldSearchElementFromArrayIfPresent() {
        int[] numbers = {1, -9, 0, 10, 288};
        int index = search.findElement(10, numbers);
        assertEquals(3, index);
    }
    // Write a Linear search to find an element in an array

    //    {200, -90, 0, 1, 7, 0, 2, 0, 19} ==> {200, -90, 1, 7, 2, 19, 0, 0, 0}
    // moveZerosRight
    @Test
    void moveZerosRight() {
        int[] numbers = numbers();
        int[] moveZeroesRight = search.moveZeros(numbers);
        System.out.println(Arrays.toString(moveZeroesRight));
        assertEquals(200, moveZeroesRight[moveZeroesRight.length-9]);

    }
}