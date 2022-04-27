package com.ogawalucas.services;

import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import com.ogawalucas.exceptions.LeaseException;
import com.ogawalucas.exceptions.MovieWithoutStockException;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.List;

// Anotation to execute tests in name asceding order:
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeaseServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        System.out.println("Before Each Test.");
    }

    @After
    public void tearDown() {
        System.out.println("After Each Test.");
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Before Class.");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("After Class.");
    }

    @Test
    public void test() throws Exception {
        // Scenery
        var user = new User("name");
        var movie = List.of(new Movie("movie", 1, 1.0));

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

    @Test(expected = MovieWithoutStockException.class)
    public void testWithoutExceptionMessageValidationWhenStockIsZero() throws LeaseException, MovieWithoutStockException {
        // Scenery
        var user = new User("name");
        var movie = List.of(new Movie("movie", 0, 1.0));

        // Action
        new LeaseService().lease(user, movie);
    }

    @Test
    public void testWithExceptionMessageValidationWhenStockIsZero() {
        // Scenery
        var user = new User("name");
        var movie = List.of(new Movie("movie", 0, 1.0));

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
    public void anotherTestWithExceptionMessageValidationWhenStockIsZero() throws LeaseException, MovieWithoutStockException {
        // Scenery
        var user = new User("name");
        var movie = List.of(new Movie("movie", 0, 1.0));

        exception.expect(MovieWithoutStockException.class);
        exception.expectMessage("Movie without stock.");

        new LeaseService().lease(user, movie);
    }

    @Test
    public void testWithExceptionMessageValidationWhenUserIsNull() {
        // Scenery
        User user = null;
        var movie = List.of(new Movie("movie", 1, 1.0));

        try {
            // Action
            new LeaseService().lease(user, movie);
            Assert.fail("It should throw an exception.");
        } catch (Exception ex) {
            // Verification
            Assert.assertThat(ex.getMessage(), CoreMatchers.is(CoreMatchers.equalTo("User empty.")));
        }
    }

    @Test
    public void testWithExceptionMessageValidationWhenUserMovieNull() throws LeaseException, MovieWithoutStockException {
        // Scenery
        var user = new User("name");
        List<Movie> movie = null;

        exception.expect(LeaseException.class);
        exception.expectMessage("Movie empty.");

        // Action
        new LeaseService().lease(user, movie);
    }
}