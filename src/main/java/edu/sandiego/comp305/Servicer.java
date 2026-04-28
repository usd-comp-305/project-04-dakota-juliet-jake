package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public abstract class Servicer {
    private String schedule;
    private int numCompleted;
    private ArrayList<Service> availableServices;

    public Servicer(){
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
