package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    /**
     * Calculate the discount based on the rules
     * @param showing of the movie
     * @return the eligible discount
     */
    private double getDiscount(Showing showing) {
        int showSequence = showing.getSequenceOfTheDay();
        double specialDiscount = (MOVIE_CODE_SPECIAL == specialCode) ? ticketPrice * 0.2 : 0; // 20% discount for special movie

        double sequenceDiscount = 0;
        switch (showSequence) {
            case 1:
                sequenceDiscount = 3; // $3 discount for 1st show
                break;
            case 2:
                sequenceDiscount = 2; // $2 discount for 2nd show
                break;
        }

        double timeDiscount = 0;
        LocalTime startTime = showing.getStartTime().toLocalTime();
        if (startTime.isAfter(LocalTime.of(11, 00)) && startTime.isBefore(LocalTime.of(16, 00))) {
            timeDiscount = ticketPrice * 0.25;
        }

        double dayDiscount = (showing.getStartTime().getDayOfMonth() == 7) ? 1 : 0;

        // biggest discount wins
        return Math.max(timeDiscount, Math.max(dayDiscount, Math.max(sequenceDiscount, specialDiscount)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}