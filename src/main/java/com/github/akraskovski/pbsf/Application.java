package com.github.akraskovski.pbsf;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.models.Role;
import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.domain.repositories.CampaignRepository;
import com.github.akraskovski.pbsf.domain.repositories.UserRepository;
import com.github.akraskovski.pbsf.security.enums.AccessLevel;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring boot's bootstrap class.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public Application(UserRepository userRepository, CampaignRepository campaignRepository) {
        this.userRepository = userRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void run(String... args) {
        var role = new Role()
            .setScopes(Set.of(Scope.CAMPAIGN_MANAGEMENT))
            .setAccessLevel(AccessLevel.COMPLETE)
            .setActions(new HashSet<>(Arrays.asList(EntityAction.values())));

        var user = new User()
            .setId("1")
            .setEmail("email@email.com")
            .setPassword("password")
            .setRole(role);

        var campaign = new Campaign()
            .setId("1")
            .setName("name")
            .setOwner(user);

        userRepository.create(user);
        campaignRepository.create(campaign);
    }

    /**
     * bootstrap method.
     *
     * @param args jvm arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
