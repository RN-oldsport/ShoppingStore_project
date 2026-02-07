package model;

public class Admin extends User {

    public Admin() {
        super();
        this.setRole(Role.ADMIN);
    }

    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);
    }

}
