package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalView implements View {
    private final Scanner scanner;

    public TerminalView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String prompt(final String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    @Override
    public void display(final String message) {
        System.out.println(message);
    }

    @Override
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

    @Override
    public void displayServices(final ServicerAccount servicer, final ArrayList<Service> services) {
        this.display("Your services:");
        for (int i = 0; i < services.size(); i++) {
            this.display((i + 1) + ". " + services.get(i).getName() +
                    " ($" + services.get(i).getPrice() + ")");
        }
    }
}