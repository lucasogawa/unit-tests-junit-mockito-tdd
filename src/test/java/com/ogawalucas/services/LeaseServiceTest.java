package com.ogawalucas.services;

import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Objects;

public class LeaseServiceTest {

    @Test
    public void test() {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 1, 1.0);

        // Action
        var lease = new LeaseService().lease(user, movie);

        // Verification
        Assert.assertEquals(1.0, lease.value(), 0.1);
        Assert.assertTrue(Objects.equals(lease.leaseDate(), LocalDate.now()));
        Assert.assertTrue(Objects.equals(lease.returnDate(), LocalDate.now().plusDays(1)));
    }
}