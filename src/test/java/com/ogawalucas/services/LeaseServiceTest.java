package com.ogawalucas.services;

import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class LeaseServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test() throws Exception {
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

    @Test(expected = Exception.class)
    public void testWithoutExceptionMessageValidation() throws Exception {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 0, 1.0);

        // Action
        new LeaseService().lease(user, movie);
    }

    @Test
    public void testWitExceptionMessageValidation() throws Exception {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 0, 1.0);

        try {
            // Action
            new LeaseService().lease(user, movie);
            Assert.fail("It should throw an exception.");
        } catch (Exception ex) {
            // Verification
            Assert.assertThat(ex.getMessage(), CoreMatchers.is(CoreMatchers.equalTo("Movie without stock.")));
        }
    }

    @Test
    public void anotherTestWitExceptionMessageValidation() throws Exception {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 0, 1.0);

        exception.expect(Exception.class);
        exception.expectMessage("Movie without stock.");

        new LeaseService().lease(user, movie);
    }
}