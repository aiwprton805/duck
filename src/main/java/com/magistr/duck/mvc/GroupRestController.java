package com.magistr.duck.mvc;

import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;
import com.magistr.duck.entity.Term;
import com.magistr.duck.service.ProfileGroupService;
import com.magistr.duck.service.ProfileService;
import com.magistr.duck.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group/")
public class GroupRestController {

    private final ProfileService profileService;
    private final ProfileGroupService profileGroupService;
    private final TermService termService;

    @Autowired
    public GroupRestController(ProfileService profileService, ProfileGroupService profileGroupService, TermService termService) {
        this.profileService = profileService;
        this.profileGroupService = profileGroupService;
        this.termService = termService;
    }

    @GetMapping("/terms")
    public List<Term> getLectorsNewTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.NEW);
    }

    @GetMapping("terms/profile/{profileId}")
    public List<Term> getStudentsTerms(@PathVariable Integer profileId){
        var profile = new Profile();
        profile.setId(profileId);
        return termService.getTerms(profile);
    }

    @PostMapping("/add-term")
    public void addTermToStudent(@RequestBody Map<String, Integer> params){
        var studentProfileId = params.get("studentProfileId");
        var termId = params.get("termId");
        profileService.addTerm(studentProfileId, termId);
        termService.getTerm(termId).ifPresent(term -> {
            term.setStatus(TermStatus.PROCESSING);
            termService.saveTerm(term);
        });
    }

    @PostMapping("{groupId}/remove-student/{profileId}")
    public void removeStudentFromGroup(@PathVariable Integer groupId, @PathVariable Integer profileId, Principal principal){
        var lectorProfile = profileService.getProfile(principal).orElseThrow();
        var studentProfile = new Profile();
        studentProfile.setId(profileId);
        var studentTerms = termService.getTerms(studentProfile);
        var lectorTerms = termService.getTerms(lectorProfile);
        studentTerms.retainAll(lectorTerms);
        for (var term: studentTerms) {
            term.setStatus(TermStatus.NEW);
            termService.saveTerm(term);
            profileService.removeTerm(profileId, term.getId());
        }
        profileGroupService.removeProfile(profileId, groupId);
    }

    @PostMapping("student/{profileId}/remove-term/{termId}")
    public void removeTermFromStudent(@PathVariable Integer profileId, @PathVariable Integer termId){
        termService.getTerm(termId).ifPresent(term -> {
            term.setStatus(TermStatus.NEW);
            termService.saveTerm(term);
            profileService.removeTerm(profileId, termId);
        });
    }

    @PostMapping("{groupId}/refresh-token")
    public ProfileGroup refreshProfileGroupToken(@PathVariable Integer groupId){
        var profileGroup = profileGroupService.getProfileGroup(groupId).orElseThrow();
        profileGroup.setToken(profileGroupService.generateToken());
        profileGroupService.saveProfileGroup(profileGroup);
        return profileGroup;
    }
}
