package com.example.springSecurityII.dto;

import java.util.Arrays;
import java.util.Random;

public class SearchFixture {

    public static int[] numbers() {
        Random random = new Random();
        int[] numbers = new int[10];
        return new int[]{200, -90, 0, 1, 7, 0, 2, 0, 19};
    }
}
