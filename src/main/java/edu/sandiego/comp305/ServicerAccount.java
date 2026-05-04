package edu.sandiego.comp305;

public abstract class ServicerAccount extends Profile {

    public ServicerAccount(final String name,
                           final String username,
                           final String password) {
        super(name, username, password);
    }

    public void update(final String customerName, final String address,
                       final Service Service){

    }

    public void takeCall(final Service service){

    }

    public void postService() {

    }
}
