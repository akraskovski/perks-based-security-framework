package com.github.akraskovski.pbsf.controllers;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.domain.repositories.CampaignRepository;
import com.github.akraskovski.pbsf.domain.repositories.UserRepository;
import com.github.akraskovski.pbsf.security.access.CoreCampaignAccessDefinition;
import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import com.github.akraskovski.pbsf.security.enums.AccessLevel;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Secured controller.
 */
@RestController
@RequestMapping("campaign")
public class CampaignController implements SecuredEntityEndpoint<Campaign> {

    private final EntityAccessDefinition entityAccessDefinition;
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    @Autowired
    public CampaignController(CoreCampaignAccessDefinition entityAccessDefinition,
                              CampaignRepository campaignRepository,
                              UserRepository userRepository) {
        this.entityAccessDefinition = entityAccessDefinition;
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Class<Campaign> getProcessingEntity() {
        return Campaign.class;
    }

    @Override
    public User findOwner(String id) {
        // todo think how to re-implement.
        return userRepository
            .findById(entityAccessDefinition.whoOwns(id))
            .get();
    }

    /**
     * Gets {@link Campaign} by id endpoint
     *
     * @return found campaign or thrown exception
     */
    @Secured(scope = Scope.CAMPAIGN_MANAGEMENT, actions = EntityAction.READ, accessLevel = AccessLevel.COMPLETE)
    @GetMapping("{id}")
    public ResponseEntity<Campaign> getById(@PathVariable String id) {
        return ResponseEntity.ok(campaignRepository.findById(id).orElseThrow());
    }
}
