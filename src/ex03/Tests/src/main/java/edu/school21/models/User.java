package edu.school21.models;

public class User {

    private int id;

    private String login;

    private String password;

    private boolean authenticationStatus;

    public User(int id, String login, String password, boolean authenticationStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticationStatus = authenticationStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(boolean authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public static class EntityNotFoundException extends RuntimeException {

        public EntityNotFoundException(String message) {
            super(message);
        }
    }
}
