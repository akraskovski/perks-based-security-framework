package com.github.akraskovski.pbsf.controllers;

import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import com.github.akraskovski.pbsf.security.enums.AccessLevel;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Secured controller.
 */
@RestController
public class CampaignController implements SecuredEntityEndpoint<String> {

    @Override
    public Class<String> getProcessingEntity() {
        return String.class;
    }

    @Override
    public Object findOwner(String s) {
        return new Object();
    }

    /**
     * Say greeting secured endpoint.
     *
     * @return some string...
     */
    @Secured(scope = Scope.CAMPAIGN_MANAGEMENT, actions = EntityAction.READ, accessLevel = AccessLevel.COMPLETE)
    @GetMapping("/campaign/{id}")
    public ResponseEntity<String> sayGreeting(@PathVariable String id) {
        return ResponseEntity.ok("You're wanna access entity with an id: " + id);
    }
}
