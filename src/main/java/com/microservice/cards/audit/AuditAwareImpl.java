package com.microservice.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Get the current user for auditing purposes. In a real application, this would typically be retrieved from the security context. For simplicity, we return a fixed username here.
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // In a real application, we would retrieve the current user from the security context
        // For simplicity, we return a fixed username here
        return Optional.of("CARDS_MS");
    }
}
