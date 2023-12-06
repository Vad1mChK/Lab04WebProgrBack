package org.vad1mchk.webprogr.lab04.model.response;

public class UserResponseDto {
    private long id;
    private String username;
    private boolean loggedIn;

    // Constructors, getters, and setters
    public UserResponseDto() {
    }

    public UserResponseDto(long id, String username, boolean loggedIn) {
        this.id = id;
        this.username = username;
        this.loggedIn = loggedIn;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}