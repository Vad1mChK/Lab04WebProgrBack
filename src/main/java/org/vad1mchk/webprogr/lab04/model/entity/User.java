package org.vad1mchk.webprogr.lab04.model.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "w_user", schema = "s322864", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User {
    private long id;
    private String username;
    private byte[] passwordHashed;
    private byte[] salt;
    private boolean loggedIn;

    public User() {
        super();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Column(name = "password_hashed")
    public byte[] getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(byte[] passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    @Column(name = "salt")
    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] saltHashed) {
        this.salt = saltHashed;
    }

    @Column(name = "logged_in")
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && loggedIn == user.loggedIn && Objects.equals(username, user.username) && Arrays.equals(passwordHashed, user.passwordHashed) && Arrays.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, loggedIn);
        result = 31 * result + Arrays.hashCode(passwordHashed);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", passwordHashed=" + Arrays.toString(passwordHashed) +
                ", saltHashed=" + Arrays.toString(salt) +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
