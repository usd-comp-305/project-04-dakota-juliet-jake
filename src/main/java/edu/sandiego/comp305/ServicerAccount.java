package edu.sandiego.comp305;

import java.util.ArrayList;

public abstract class ServicerAccount extends Profile {
    private String schedule;

    private int numCompleted;

    private ArrayList<Service> availableServices;

    public ServicerAccount(){
        this.schedule = "";
        this.numCompleted = 0;
        this.availableServices = new ArrayList<>();
    }

    public void update(final String customerName, final String address,
                       final Service Service){

    }

    public void takeCall(final Service service){

    }

    public void postService() {

    }
}
