package com.nschejtman.utils;

import com.nschejtman.model.User;
import org.apache.commons.text.similarity.LevenshteinDistance;

import javax.validation.constraints.NotNull;
import java.util.*;

public class UserDao {
    private static Map<String, User> users = new HashMap<String, User>();

    public static boolean validate(@NotNull String username, @NotNull String password) {
        final User user = users.get(username);
        if (user == null) {
            return false;
        }
        final String retrievedPassword = user.getPassword();
        return retrievedPassword != null && retrievedPassword.equals(password);
    }

    public static void register(@NotNull String username, @NotNull String password) {
        final User user = new User(username, password);
        users.put(username, user);
    }

    public static User get(@NotNull String username) {
        return users.get(username);
    }

    public static List<String> search(String searchText) {
        final Set<String> usernames = users.keySet();
        final ArrayList<String> result = new ArrayList<>();
        for (String username : usernames) {
            final LevenshteinDistance distanceCalculator = new LevenshteinDistance(null);
            final Integer distance = distanceCalculator.apply(username, searchText);
            if (distance < 3)
                result.add(username);
        }
        return result;
    }


}