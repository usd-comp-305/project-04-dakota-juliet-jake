package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

class CustomerViewTest {

    @Test
    public void testSetStrategy() {
        final CustomerView view =
                new CustomerView(Mockito.mock(DisplayStrategy.class));
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        view.setStrategy(mockStrategy);
        view.render();
        Mockito.verify(mockStrategy).display();
    }

    @Test
    public void testRender() {
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        final CustomerView view = new CustomerView(mockStrategy);
        view.render();
        Mockito.verify(mockStrategy).display();
    }

    @Test
    public void testShowServiceList() {
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        final CustomerView view = new CustomerView(mockStrategy);
        final Listing mockListing = Mockito.mock(Listing.class);
        final List<Listing> listings = new ArrayList<>();
        listings.add(mockListing);
        view.showServiceList(listings);
        Mockito.verify(mockStrategy).display();
    }

    @Test
    public void testShowSearchResults() {
        final DisplayStrategy mockStrategy =
                Mockito.mock(DisplayStrategy.class);
        final CustomerView view = new CustomerView(mockStrategy);
        final Listing mockListing = Mockito.mock(Listing.class);
        Mockito.when(mockListing.getProviderName()).thenReturn("Jake");
        final List<Listing> results = new ArrayList<>();
        results.add(mockListing);
        view.showSearchResults(results);
        Mockito.verify(mockListing).getProviderName();
    }
}
