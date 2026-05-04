package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {

    Service service = new Service();

    @Test
    public void setNameTest(){
        service.setName("Barber");
        assertEquals("Barber", service.getName());
    }

    @Test
    public void setPriceTest(){
        service.setPrice(25);
        assertEquals(25, service.getPrice());
    }

    @Test
    public void constructorTest(){
        final Service fastService = new Service("Barber", 25);
        assertEquals("Barber", fastService.getName());
        assertEquals(25, fastService.getPrice());
    }
}
