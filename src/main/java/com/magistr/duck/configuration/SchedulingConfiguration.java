package com.magistr.duck.configuration;

import com.magistr.duck.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    private final ProposalService proposalService;

    @Autowired
    public SchedulingConfiguration(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    //Every day at 00:00 o'clock from Monday to Friday at Minsk time
    @Scheduled(cron = "0 0 * * 1-5 ?", zone = "Europe/Minsk")
    public void removeRejectedAndProcessedProposals() {
        proposalService.removeAllWithRejectedAndProcessedStatuses();
    }
}
//class
