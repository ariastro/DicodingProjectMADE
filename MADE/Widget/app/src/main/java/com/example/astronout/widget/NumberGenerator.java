package com.example.astronout.widget;

import java.util.Random;

public class NumberGenerator {

    public static int Generate(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
}
