package com.ogawalucas.services;

import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import com.ogawalucas.exceptions.LeaseException;
import com.ogawalucas.exceptions.MovieWithoutStockException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class LeaseValueCalculationServiceTest {

    private LeaseService service;

    @Parameterized.Parameter
    public List<Movie> movies;
    @Parameterized.Parameter(value = 1)
    public Double leaseValue;
    @Parameterized.Parameter(value = 2)
    public String scenery;

    @Before
    public void setUp() {
        service = new LeaseService();
    }

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParameters() {
        var movie1 = new Movie("Movie 1", 2, 4.0);
        var movie2 = new Movie("Movie 2", 2, 4.0);
        var movie3 = new Movie("Movie 3", 2, 4.0);
        var movie4 = new Movie("Movie 4", 2, 4.0);
        var movie5 = new Movie("Movie 5", 2, 4.0);
        var movie6 = new Movie("Movie 6", 2, 4.0);
        var movie7 = new Movie("Movie 7", 2, 4.0);

        return List.of(
            new Object[][] {
                {List.of(movie1, movie2), 8.0, "Second Movie = 0% OFF"},
                {List.of(movie1, movie2, movie3), 11.0, "Third Movie = 25% OFF"},
                {List.of(movie1, movie2, movie3, movie4), 13.0, "Fourth Movie = 50% OFF"},
                {List.of(movie1, movie2, movie3, movie4, movie5), 14.0, "Fifth Movie = 75% OFF"},
                {List.of(movie1, movie2, movie3, movie4, movie5, movie6), 14.0, "Sixth Movie = 75% OFF"},
                {List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7), 18.0, "Seventh Movie = 75% OFF"},
            }
        );
    }

    @Test
    public void shouldReturnValueWithDiscount() throws LeaseException, MovieWithoutStockException {
        // Scenery
        var user = new User("name");

        // Action
        var lease = service.lease(user, movies);

        // Verification
        Assert.assertThat(lease.value(), CoreMatchers.is(CoreMatchers.equalTo(leaseValue)));
    }
}
