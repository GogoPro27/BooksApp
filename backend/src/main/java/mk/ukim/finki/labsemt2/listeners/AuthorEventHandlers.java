package mk.ukim.finki.labsemt2.listeners;

import mk.ukim.finki.labsemt2.events.AuthorChangedEvent;
import mk.ukim.finki.labsemt2.events.AuthorCreatedEvent;
import mk.ukim.finki.labsemt2.events.AuthorDeletedEvent;
import mk.ukim.finki.labsemt2.service.application.ICountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {
    private final ICountryApplicationService countryApplicationService;

    public AuthorEventHandlers(ICountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }
    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
