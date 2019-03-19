package com.github.akraskovski.pbsf.domain.repositories;

import com.github.akraskovski.pbsf.domain.models.Campaign;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Campaign repository.
 */
@Repository
public class CampaignRepository {

    /**
     * The constant staticCampaigns.
     */
    private static Map<String, Campaign> staticCampaigns = new HashMap<>();

    public Campaign create(Campaign Campaign) {
        staticCampaigns.put(Campaign.getId(), Campaign);
        return Campaign;
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Campaign> findById(String id) {
        return Optional.ofNullable(staticCampaigns.get(id));
    }
}
