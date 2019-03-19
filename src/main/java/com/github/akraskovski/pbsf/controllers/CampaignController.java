package com.github.akraskovski.pbsf.controllers;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.repositories.CampaignRepository;
import com.github.akraskovski.pbsf.security.access.CoreCampaignAccessDefinition;
import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Secured controller.
 */
@RestController
@RequestMapping("campaign")
public class CampaignController implements SecuredEntityEndpoint {

    private final EntityAccessDefinition entityAccessDefinition;
    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignController(CoreCampaignAccessDefinition entityAccessDefinition,
                              CampaignRepository campaignRepository) {
        this.entityAccessDefinition = entityAccessDefinition;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public EntityAccessDefinition getEntityAccessDefinition() {
        return entityAccessDefinition;
    }

    @Secured(scope = Scope.CAMPAIGN_MANAGEMENT, actions = EntityAction.READ)
    @GetMapping("{id}")
    public ResponseEntity<Campaign> getById(@PathVariable String id) {
        return ResponseEntity.ok(campaignRepository.findById(id).orElseThrow());
    }

    @Secured(scope = Scope.CAMPAIGN_MANAGEMENT, actions = EntityAction.WRITE)
    @PostMapping
    public ResponseEntity<Campaign> create(Campaign campaign) {
        return ResponseEntity.ok(campaignRepository.create(campaign));
    }

    @Secured(scope = Scope.CAMPAIGN_MANAGEMENT, actions = EntityAction.OPERATE)
    @PutMapping("{id}")
    public ResponseEntity<Void> activate(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
