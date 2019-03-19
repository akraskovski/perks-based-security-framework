/*
 * This portion of the source code is copyrighted by Thumbtack Technology LLC,
 * all rights reserved, and is subject to the terms of a license agreement
 * in which it constitutes “Pre-existing work” or "Licensed Product".
 */
package com.github.akraskovski.pbsf.security.access;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.domain.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CoreCampaignAccessDefinition.
 */
@Component
public class CoreCampaignAccessDefinition implements EntityAccessDefinition {

    private final CampaignRepository repository;

    @Autowired
    public CoreCampaignAccessDefinition(CampaignRepository repository) {
        this.repository = repository;
    }

    @Override
    public String entityName() {
        return Campaign.class.getSimpleName();
    }

    @Override
    public String whoOwns(String entityId) {
        return repository.findById(entityId)
            .map(Campaign::getOwner)
            .map(User::getId)
            .orElseThrow(() -> new RuntimeException("EntityNotFoundException"));
    }
}
