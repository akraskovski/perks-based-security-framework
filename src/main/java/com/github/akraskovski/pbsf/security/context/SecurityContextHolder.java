package com.github.akraskovski.pbsf.security.context;

import com.github.akraskovski.pbsf.domain.models.User;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * User's security context holder class.
 */
public final class SecurityContextHolder {

    private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

    private SecurityContextHolder() {
        // nothing to do
    }

    /**
     * Clear context.
     */
    public static void clearContext() {
        CONTEXT.remove();
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Optional<User> getContext() {
        return Optional.ofNullable(CONTEXT.get());
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public static void setContext(User context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        SecurityContextHolder.CONTEXT.set(context);
    }
}
