package website.Login;

public class User {
    private String name;
    private boolean loggedIn;
    private String username;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setLoggedIn(boolean loggedIn){this.loggedIn = loggedIn;}

    public boolean getLoggedIn(){return loggedIn;}

    public void setName(String name) {
        this.name = name;
    }
}
