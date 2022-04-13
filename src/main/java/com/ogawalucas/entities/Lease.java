package com.ogawalucas.entities;

import java.time.LocalDateTime;

public record Lease(User user, Movie movie, LocalDateTime leaseLocation, LocalDateTime leaseReturn, Double value) {

}