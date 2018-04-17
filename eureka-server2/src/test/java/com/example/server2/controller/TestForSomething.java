package com.example.server2.controller;

/**
 * @author zhenyu.guo
 * @date 2018/3/6.
 */
public class TestForSomething {

    public static void main(String[] args) {

        int a = 1;
        System.out.println(getA(a));

    }

    public static int getA(int a) {
        try{
            a++;
            return a;
        } finally {
            a++;
            return a;

        }
    }

}
