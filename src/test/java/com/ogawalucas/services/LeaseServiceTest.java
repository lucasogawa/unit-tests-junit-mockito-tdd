package com.ogawalucas.services;

import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.time.LocalDate;

public class LeaseServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void test() {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 1, 1.0);

        // Action
        var lease = new LeaseService().lease(user, movie);

        // Verification without rastreabilidad
        Assert.assertThat(lease.value(), CoreMatchers.is(CoreMatchers.equalTo(1.0)));
        Assert.assertThat(lease.leaseDate(), CoreMatchers.is(CoreMatchers.equalTo(LocalDate.now())));
        Assert.assertThat(lease.returnDate(), CoreMatchers.is(CoreMatchers.equalTo(LocalDate.now().plusDays(1))));

        // Verification with rastreabilidad
        error.checkThat(lease.value(), CoreMatchers.is(CoreMatchers.equalTo(1.0)));
        error.checkThat(lease.leaseDate(), CoreMatchers.is(CoreMatchers.equalTo(LocalDate.now())));
        error.checkThat(lease.returnDate(), CoreMatchers.is(CoreMatchers.equalTo(LocalDate.now().plusDays(1))));
    }
}