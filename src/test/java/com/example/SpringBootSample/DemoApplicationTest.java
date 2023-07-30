package com.example.SpringBootSample;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoApplicationTest {

    private final DemoApplication target = new DemoApplication();

    @Nested
    class calc {
        @Test
        void 値が2の倍数のパターン() {
            int result = target.calc(2);
            assertEquals(6,result);
        }

//        @Disabled
        @Test
        void 値が3の倍数のパターン() {
            int result = target.calc(3);
            assertEquals(9,result);
        }

        //@Disabled
        @Test
        void 値が2かつ3で割り切れないパターン() {
            int result = target.calc(5);
            assertEquals(10, result);
        }
    }
}