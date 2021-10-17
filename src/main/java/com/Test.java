package com;


import org.apache.log4j.Logger;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello world!");

        Logger logger = Logger.getLogger(Test.class);

        logger.info("test");

    }
}
