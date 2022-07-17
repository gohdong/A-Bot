package net.aibot.demo.util;

import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Date;

public class IDGenerator {
    public static long makeRandomId() {
        return new Date().getTime() * 100L + new RandomDataGenerator().nextLong(0L, 99L);
    }
}
