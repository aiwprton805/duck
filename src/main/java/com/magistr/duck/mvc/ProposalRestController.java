package com.magistr.duck.mvc;

import com.magistr.duck.common.enums.ProposalStatus;
import com.magistr.duck.dto.LectorProfile;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.Proposal;
import com.magistr.duck.service.ProfileService;
import com.magistr.duck.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proposal")
public class ProposalRestController {
    private final ProposalService proposalService;
    private final ProfileService profileService;

    @Autowired
    public ProposalRestController(ProposalService proposalService, ProfileService profileService) {
        this.proposalService = proposalService;
        this.profileService = profileService;
    }

    @GetMapping("/proposals/start/{start}/length/{length}")
    public Map<String, Object> getProposals(@PathVariable Integer start,
                                            @PathVariable Integer length,
                                            Authentication authentication,
                                            Principal principal) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<Proposal> proposals;
        if (roles.contains("admin") && roles.contains("lector")) {
            proposals = proposalService.getProposalsByStatus(ProposalStatus.NEW);
            proposals.addAll(proposalService.getProposalsByLector(profileService.getProfile(principal).orElseGet(Profile::new)));
        } else if (roles.contains("admin")) {
            proposals = proposalService.getProposalsByStatus(ProposalStatus.NEW);
        } else if (roles.contains("lector")) {
            proposals = proposalService.getProposalsByLector(profileService.getProfile(
                    (Principal) authentication.getPrincipal()).orElseGet(Profile::new));
        } else {
            proposals = new ArrayList<>(0);
        }
        Integer proposalsSize = proposals.size();
        return Map.of("data", proposals.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", proposalsSize);
    }

    @GetMapping("/lectors")
    public List<LectorProfile> getLectors() {
        return profileService.getLectorProfiles();
    }

    @PostMapping("/{proposalId}/bind-to-lector/{lectorProfileId}")
    public void bindProposalToLector(@PathVariable Integer proposalId,
                                     @PathVariable Integer lectorProfileId) {
        proposalService.bindProposalToLector(proposalId, lectorProfileId);
    }
}
//class
