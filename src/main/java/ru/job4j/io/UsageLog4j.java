package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        byte b = 0x55;
        short s = 0x55ff;
        long l = 0xffffffffL;
        char c = 'a';
        float f = .25f;
        double d =.00001234;
        boolean bool = true;
        LOG.debug("age : {}, b : {}, s : {}, l : {}, c : {}, f : {}, d : {}, bool : {}", age, b, s, l, c, f, d, bool);
    }
}
