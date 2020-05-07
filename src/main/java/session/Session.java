package session;

import model.User;

import java.io.Serializable;

public class Session implements Serializable {
    private static  User user;



    public static User getUser() {
        return user;
    }

    public static void setUser(User user1) {
        user = user1;
    }

}
