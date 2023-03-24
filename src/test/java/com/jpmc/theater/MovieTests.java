package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void firstShowSequenceWithThreeDollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(9.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void secondShowSequenceWithTwoDollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void showBetween11and4With25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,30)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void showOn7thDayOfTheMonthWithOneDollarDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 10, 7), LocalTime.of(10,30)));
        assertEquals(9, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void showWithAllDiscountsGetsTheOneWithMaximumDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.of(2022, 10, 7), LocalTime.of(11,30)));
        // Gets the Showing Sequence discount
        assertEquals(7, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void showWithNoDiscountsGetsZeroDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 10, 6), LocalTime.of(10,30)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }
}
