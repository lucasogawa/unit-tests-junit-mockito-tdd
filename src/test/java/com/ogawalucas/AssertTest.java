package com.ogawalucas;

import com.ogawalucas.entities.User;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals("Comparation error", 1, 1);
        Assert.assertEquals(0.51234, 0.512, 0.001);

        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "casa");

        var u1 = new User("Usuario 1");
        var u2 = new User("Usuario 1");
        User u3 = null;

        Assert.assertEquals(u1, u2);

        Assert.assertSame(u1, u1);
        Assert.assertNotSame(u1, u2);

        Assert.assertNull(u3);
        Assert.assertNotNull(u2);
    }
}
