package com.github.akraskovski.pbsf.domain.repositories;

import com.github.akraskovski.pbsf.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public class UserRepository {

    /**
     * The constant staticUsers.
     */
    private static Map<String, User> staticUsers = new HashMap<>();

    public User create(User user) {
        staticUsers.put(user.getId(), user);
        return user;
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<User> findById(String id) {
        return Optional.ofNullable(staticUsers.get(id));
    }
}
