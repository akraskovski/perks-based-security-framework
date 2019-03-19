package com.github.akraskovski.pbsf.security.context;

import org.springframework.util.Assert;

import java.util.Optional;

/**
 * User's security context holder class.
 */
public class SecurityContextHolder {

    //todo instead of Object here must be authorized user instance.
    private static final ThreadLocal<Object> context = new ThreadLocal<>();

    public void clearContext() {
        context.remove();
    }

    public Object getContext() {
        return Optional.ofNullable(context.get())
            .orElseGet(() -> {
                var ctx = createEmptyContext();
                context.set(ctx);

                return ctx;
            });
    }

    public void setContext(SecurityContextHolder context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        SecurityContextHolder.context.set(context);
    }

    private Object createEmptyContext() {
        return new Object();
    }
}
