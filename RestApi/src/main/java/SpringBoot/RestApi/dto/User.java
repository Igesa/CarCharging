package SpringBoot.RestApi.dto;

public class User {
    String name;
    String surname;
    String userId;

    public User (String name, String surname, String userID) {
        this.name = name;
        this.surname = surname;
        this.userId = userID;
    }

    public User() {
    }

    public User(String userID) {
        this.userId = userID;
    }
}
