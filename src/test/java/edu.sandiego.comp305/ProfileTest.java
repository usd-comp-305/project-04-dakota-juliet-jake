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
    public void setNameNullTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setName(""));
    }

    @Test
    public void setUsernameTest(){
        profile.setUsername("ldemman");
        assertEquals("ldemman", profile.getUsername());
    }

    @Test
    public void setUsernameNullTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setUsername(""));
    }

    @Test
    public void setPasswordPassTest(){
        profile.setPassword("Passw0rd!");
        assertEquals("Passw0rd!", profile.getPassword());
    }

    @Test
    public void setPasswordNullTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword(""));
    }

    @Test
    public void setPasswordNoUppercaseTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword("passw0rd!"));
    }

    @Test
    public void setPasswordNoLowercaseTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword("PASSW0RD!"));
    }

    @Test
    public void setPasswordNoNumberTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword("Password!"));
    }

    @Test
    public void setPasswordNoSpecialCharacterTest(){
        assertThrows(IllegalArgumentException.class, () -> profile.setPassword("Passw0rd"));
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
