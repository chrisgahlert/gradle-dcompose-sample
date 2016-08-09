package com.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class MongoDbCounterTest {
    @Test
    public void testGetCount() throws Exception {
        String first = MongoDbCounter.getCount();
        assertEquals("times invoked: 1", first);

        String second = MongoDbCounter.getCount();
        assertTrue(second.startsWith("times invoked: 2 (last invocation was at"));
    }
}