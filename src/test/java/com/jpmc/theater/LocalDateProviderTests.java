package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        LocalDateProvider provider = new LocalDateProvider();
        LocalDate expected = LocalDate.now();
        LocalDate actual = provider.currentDate();
        assertEquals(expected, actual);
    }
}
