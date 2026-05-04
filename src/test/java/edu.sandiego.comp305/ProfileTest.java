package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProfileTest {

    //need a concrete subclass to test everything
    static class TestProfile extends Profile {
        public TestProfile(){
            super();
        }
    }

    Profile profile = new TestProfile();

    @Test
    public void setNameTest(){
        profile.setName("Lily Demman");
        assertEquals("Lily Demman", profile.getName());
    }

    @Test
    public void setUsernameTest(){
        profile.setUsername("ldemman");
        assertEquals("ldemman", profile.getUsername());
    }

    @Test
    public void setPasswordPassTest(){
        profile.setPassword("Passw0rd!");
        assertEquals("Passw0rd!", profile.getPassword());
    }

    @Test
    public void setPasswordFailTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword("password"));
    }

    @Test
    public void adjustRatingTest(){
        profile.adjustRating(1.0);
        profile.adjustRating(5.0);
        assertEquals(3.0, profile.getRating());
    }

    @Test
    public void adjustRatingFailedTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.adjustRating(6.0));
    }
}
