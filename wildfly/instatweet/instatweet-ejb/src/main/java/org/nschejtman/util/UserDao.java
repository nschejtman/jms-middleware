package org.nschejtman.util;

import org.nschejtman.model.User;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, User> users = new HashMap<String, User>();

    public static boolean validate(@NotNull String username, @NotNull String password) {
        final String retrievedPassword = users.get(username).getPassword();
        return retrievedPassword != null && retrievedPassword.equals(password);
    }

    public static void register(@NotNull String username, @NotNull String password) {
        final User user = new User(username, password);
        users.put(username, user);
    }

    public static User get(@NotNull String username){
        return users.get(username);
    }


}