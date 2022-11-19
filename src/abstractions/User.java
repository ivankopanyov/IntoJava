package abstractions;

public interface User {

    String getId();

    void setId(String id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    <TUser extends User> TUser copy();
}
