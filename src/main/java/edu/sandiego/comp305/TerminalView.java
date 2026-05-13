package edu.sandiego.comp305;

import java.util.List;
import java.util.Scanner;

public class TerminalView {
    private final Scanner scanner;

    public TerminalView() {
        this.scanner = new Scanner(System.in);
    }

    public String prompt(final String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public void display(final String message) {
        System.out.println(message);
    }

    public void displayListings(final List<ServicerAccount.Listing> listings) {
        for (int i = 0; i < listings.size(); i++) {
            ServicerAccount.Listing listing = listings.get(i);
            System.out.printf("%d. %-15s | %-12s | %s ($%.2f)%n",
                    i + 1,
                    listing.getProviderName(),
                    listing.getGeneralServiceType(),
                    listing.getServiceOffered().getName(),
                    listing.getServiceOffered().getPrice());
        }
    }
}