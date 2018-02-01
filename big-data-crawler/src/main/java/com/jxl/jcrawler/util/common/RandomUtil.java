package com.jxl.jcrawler.util.common;

import java.util.Random;
import java.util.UUID;

/**
 * Created by amosli on 16/3/31.
 */
public class RandomUtil {

    private RandomUtil(){
        throw new IllegalAccessError("Utility class");
    }

    public static synchronized int generate(int start, int end) {
        Random random = new Random();
        int result = random.nextInt(end);
        if (result < start) {
            result = start + (int) (Math.random() * (end - start + 1));
        }
        return result;
    }

    public static synchronized int getRandomInt(int a, int b) {
        if (a > b || a < 0) {
            return -1;
            }
        return a + (int) (Math.random() * (b - a + 1));
    }

    public static synchronized int generateSpecifyDigits(int num) {
        int start = 0;
        int end = 0;
        if (num < 2) {
            return generate(0, 10);
        }
        start = Integer.valueOf(StringUtil.generateCopies("1", num - 2)) * 9;
        end = Integer.valueOf(StringUtil.generateCopies("1", num - 1)) * 9;
        return generate(start, end);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
