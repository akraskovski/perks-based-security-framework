package com.github.akraskovski.pbsf.controllers;

import com.github.akraskovski.pbsf.security.actions.campaign.CampaignRead;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Secured controller.
 */
@RestController("campaign")
public class CampaignController {

    /**
     * Say greeting secured endpoint.
     *
     * @return some string...
     */
    @Secured(scope = Secured.Scope.CAMPAIGN_MANAGEMENT, actions = CampaignRead.class)
    @GetMapping("{id}")
    public ResponseEntity<String> sayGreeting(@PathVariable String id) {
        return ResponseEntity.ok("You're wanna access entity with an id: , " + id);
    }
}
