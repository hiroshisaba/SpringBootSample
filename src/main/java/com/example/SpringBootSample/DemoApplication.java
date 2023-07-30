package com.example.SpringBootSample;

public class DemoApplication {

    public int calc(int num) {
        int result;
        if (num % 2 == 0 || num % 3 == 0 ) {
            result = num * 3;
        } else {
            result = num * 2;
        }
        return result;
    }
}
