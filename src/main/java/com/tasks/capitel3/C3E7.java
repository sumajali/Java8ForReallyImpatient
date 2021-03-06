package com.tasks.capitel3;

import com.tasks.Exercise;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

import static com.tasks.capitel3.CompareOptions.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * Write a method that generates a Comparator<String> that can be normal or reversed,
 * case-sensitive or case-insensitive, space-sensitive or space-insensitive,
 * or any combination thereof. Your method should return a lambda expression.
 */
public class C3E7 implements Exercise {

    @Test
    @Override
    public void perform() {
        String[] values = {
                "BBB",
                " ccc",
                "aaa"
        };
        Arrays.sort(values, comparatorGenerator(EnumSet.of(
                REVERSE,
                CASE_INSENSITIVE,
                SPACE_INSENSITIVE
        )));
        String[] expectedArray = {
                " ccc",
                "BBB",
                "aaa"
        };
        assertArrayEquals(values, expectedArray);
        Arrays.sort(values, comparatorGenerator(EnumSet.noneOf(CompareOptions.class)));
        assertArrayEquals(values, expectedArray);
    }

    public Comparator<String> comparatorGenerator(EnumSet<CompareOptions> options) {
        return (x, y) -> {
            if (options.contains(CASE_INSENSITIVE)) {
                x = x.toLowerCase();
                y = y.toLowerCase();
            }
            if (options.contains(SPACE_INSENSITIVE)) {
                x = x.replaceAll("\\s+", "");
                y = y.replaceAll("\\s+", "");
            }
            return options.contains(REVERSE) ? y.compareTo(x) : x.compareTo(y);
        };
    }

}

enum CompareOptions {
    REVERSE,
    CASE_INSENSITIVE,
    SPACE_INSENSITIVE
}

