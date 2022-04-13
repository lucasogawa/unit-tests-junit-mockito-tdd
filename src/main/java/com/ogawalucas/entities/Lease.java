package com.ogawalucas.entities;

import java.time.LocalDate;

public record Lease(User user, Movie movie, LocalDate leaseDate, LocalDate returnDate, Double value) {

}