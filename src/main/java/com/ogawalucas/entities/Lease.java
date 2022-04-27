package com.ogawalucas.entities;

import java.time.LocalDate;
import java.util.List;

public record Lease(User user, List<Movie> movies, LocalDate leaseDate, LocalDate returnDate, Double value) {

}