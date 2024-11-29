package com.example.task01;

import java.io.IOException;
import java.io.InputStream;

public class Task01Main {
    public static void main(String[] args) throws IOException {

        // TODO С корректно реализованным классом Pair должен компилироваться и успешно работать следующий код:
//        Pair<String, Integer> p = new Pair<String,Integer>("well", 9);
//        Pair<String, Integer> p1 = new Pair<String,Integer>("well", 9);
//
//        System.out.println(p.equals(p1));

        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        pair.ifPresent((first, second) -> {
            System.out.println(first);
            System.out.println(second);
        });

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        assert(mustBeTrue == mustAlsoBeTrue);
    }

}
