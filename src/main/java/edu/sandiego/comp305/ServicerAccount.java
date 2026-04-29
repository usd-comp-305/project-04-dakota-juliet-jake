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
    public void update(String customerName, String address, Service Service){

    }

    public void takeCall(Service service){

    }

    public void postService() {

    }
}
