package mk.ukim.finki.labsemt2.jobs;

import mk.ukim.finki.labsemt2.service.application.IAuthorApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final IAuthorApplicationService authorApplicationService;

    public ScheduledTasks(IAuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.authorApplicationService.refreshMaterializedView();
    }
}
