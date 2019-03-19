package com.github.akraskovski.pbsf.security.configs;

import com.github.akraskovski.pbsf.domain.repositories.UserRepository;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.aspects.SecuredRequestsAccessAspect;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import com.github.akraskovski.pbsf.security.managers.SecuredAccessManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Basic security module configuration.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecurityConfiguration {

    private static List<String> securedEndpointBeanNames;

    /**
     * Secured requests access aspect.
     *
     * @return the secured requests access aspect
     */
    @Bean
    public SecuredRequestsAccessAspect securedRequestsAccessAspect(SecuredAccessManager accessManager,
                                                                   UserRepository userRepository) {
        return new SecuredRequestsAccessAspect(accessManager, userRepository);
    }

    @Bean
    public static BeanFactoryPostProcessor securedAnnotationEntitiesPostProcessor() {
        return beanFactory -> {
            try {
                List<String> beanClassNames = Stream.of(beanFactory.getBeanNamesForType(SecuredEntityEndpoint.class))
                        .map(beanFactory::getBeanDefinition)
                        .map(BeanDefinition::getBeanClassName)
                        .collect(Collectors.toList());
                for (String className : beanClassNames) {
                    Stream.of(Class.forName(className).getMethods())
                            .peek(method -> method.setAccessible(true))
                            .map(method -> method.getAnnotation(Secured.class))
                            .filter(Objects::nonNull)
                            .filter(annotation -> annotation.entities().length == 0)
                            .forEach(oldAnnotation -> {
                                var entitiesFromScope = oldAnnotation.scope().getEntityTypes();
                                // doesn't work... find another way
                                changeAnnotationValue(oldAnnotation, "entities", entitiesFromScope.toArray(Class[]::new));
                            });
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static void changeAnnotationValue(Annotation annotation, String key, Object newValue) {
        var handler = Proxy.getInvocationHandler(annotation);
        final Field field;

        try {
            field = handler.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException e) {
            throw new IllegalStateException(e);
        }

        final Map<String, Object> memberValues;
        try {
            memberValues = (Map<String, Object>) field.get(handler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }

        var oldValue = memberValues.get(key);
        if (oldValue == null || oldValue.getClass() != newValue.getClass()) {
            throw new IllegalArgumentException();
        }

        memberValues.put(key, newValue);
    }
}
