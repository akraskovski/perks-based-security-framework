package com.github.akraskovski.pbsf;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.domain.models.access.levels.CompleteAccessLevel;
import com.github.akraskovski.pbsf.domain.models.access.levels.OwnershipAccessLevel;
import com.github.akraskovski.pbsf.domain.models.roles.AdminRole;
import com.github.akraskovski.pbsf.domain.models.roles.ExecutorRole;
import com.github.akraskovski.pbsf.domain.models.roles.OperatorRole;
import com.github.akraskovski.pbsf.domain.models.roles.SupervisorRole;
import com.github.akraskovski.pbsf.domain.repositories.CampaignRepository;
import com.github.akraskovski.pbsf.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        var admin = new User()
            .setId("admin")
            .setEmail("email@email.com")
            .setPassword("password")
            .setRole(new AdminRole())
            .setAccessLevel(new CompleteAccessLevel());
        var operator = new User()
            .setId("operator")
            .setEmail("email@email.com")
            .setPassword("password")
            .setRole(new OperatorRole())
            .setAccessLevel(new OwnershipAccessLevel());
        var executor = new User()
            .setId("executor")
            .setEmail("email@email.com")
            .setPassword("password")
            .setRole(new ExecutorRole())
            .setAccessLevel(new OwnershipAccessLevel());
        var supervisor = new User()
            .setId("supervisor")
            .setEmail("email@email.com")
            .setPassword("password")
            .setRole(new SupervisorRole())
            .setAccessLevel(new OwnershipAccessLevel());

        var campaign = new Campaign()
            .setId("1")
            .setName("name")
            .setOwner(admin);

        userRepository.create(admin);
        userRepository.create(operator);
        userRepository.create(executor);
        userRepository.create(supervisor);
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
