package model;

import abstractions.User;

import java.util.Objects;

public class IdentityUser implements User {

    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    public IdentityUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public IdentityUser(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        lastName = value;
    }

    @Override
    public IdentityUser copy() {
        IdentityUser user = new IdentityUser(id, email, null);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IdentityUser user = (IdentityUser) o;

        return id.equals(user.getId()) || email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName + " (" + email + ')';
    }
}
