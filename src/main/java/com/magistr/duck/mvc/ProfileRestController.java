package com.magistr.duck.mvc;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.enums.TermStatus;
import com.magistr.duck.common.exceptions.AddProfileToProfileGroupException;
import com.magistr.duck.common.exceptions.ProfileGroupNotFoundException;
import com.magistr.duck.dto.UserProfile;
import com.magistr.duck.entity.*;
import com.magistr.duck.security.SecurityService;
import com.magistr.duck.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class ProfileRestController {

    private final UserService userService;
    private final ProfileService profileService;
    private final UserProfileService userProfileService;
    private final ProfileGroupService profileGroupService;
    private final ProposalService proposalService;
    private final TermService termService;
    private final SecurityService securityService;

    @Autowired
    public ProfileRestController(UserService userService, ProfileService profileService, UserProfileService userProfileService, ProfileGroupService profileGroupService, ProposalService proposalService, TermService termService, SecurityService securityService) {
        this.userService = userService;
        this.profileService = profileService;
        this.userProfileService = userProfileService;
        this.profileGroupService = profileGroupService;
        this.proposalService = proposalService;
        this.termService = termService;
        this.securityService = securityService;
    }

    @GetMapping("/admin/guests")
    public Map<String, Object> getGuests(@RequestParam("start") Integer start,
                                         @RequestParam("length") Integer length) {
        List<UserProfile> userProfiles = userProfileService.getGuests();
        Integer userProfilesSize = userProfiles.size();
        return Map.of("data", userProfiles.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", userProfilesSize);
    }

    @GetMapping("/admin/students")
    public Map<String, Object> getStudents(@RequestParam("start") Integer start,
                                           @RequestParam("length") Integer length) {
        List<UserProfile> userProfiles = userProfileService.getStudents();
        Integer userProfilesSize = userProfiles.size();
        return Map.of("data", userProfiles.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", userProfilesSize);
    }

    @GetMapping("/admin/lectors")
    public Map<String, Object> getLectors(@RequestParam("start") Integer start,
                                          @RequestParam("length") Integer length) {
        List<UserProfile> lectors = userProfileService.getLectors();
        Integer lectorsSize = lectors.size();
        return Map.of("data", lectors.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", lectorsSize);
    }

    @GetMapping("/admin/all")
    public Map<String, Object> getAll(@RequestParam("start") Integer start,
                                      @RequestParam("length") Integer length) {
        List<UserProfile> userProfiles = userProfileService.getUserProfiles();
        Integer userProfilesSize = userProfiles.size();
        return Map.of("data", userProfiles.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", userProfilesSize);
    }

    @GetMapping("/admin/proposals")
    public Map<String, Object> getProposals(@RequestParam("start") Integer start,
                                            @RequestParam("length") Integer length) {
        List<Proposal> proposals = proposalService.getProposals();
        Integer proposalsSize = proposals.size();
        return Map.of("data", proposals.stream().skip(start).limit(length).collect(Collectors.toList()),
                "size", proposalsSize);
    }

    @PostMapping("/admin/user")
    public void updateUser(@RequestBody User user){
        userService.saveUser(user);
        securityService.invalidateUserSession(user); //because user roles could change
    }

    @GetMapping("/lector/groups")
    public List<ProfileGroup> getLectorProfileGroups(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return profileGroupService.getLectorProfileGroups(profile);
    }

    @PostMapping("/lector/group")
    public ProfileGroup saveProfileGroup(@RequestBody Map<String, String> params, Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        var token = RandomStringUtils.randomAlphanumeric(6);
        var profileGroup = new ProfileGroup(params.get("name"), token, List.of(profile));
        profileGroupService.saveProfileGroup(profileGroup);
        return profileGroup;
    }

    @PostMapping("/lector/group/delete/{groupId}")
    public void removeProfileGroup(@PathVariable Integer groupId){
        profileGroupService.removeProfileGroup(groupId);
    }

    @GetMapping("/lector/terms/new")
    public List<Term> getNewTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.NEW);
    }

    @GetMapping("/lector/terms/checking")
    public List<Term> getCheckingTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.CHECKING);
    }

    @GetMapping("/lector/terms/ru")
    public List<Term> getLectorsRussianTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, Lang.RU)
                .stream()
                .filter(term -> term.getStatus().equals(TermStatus.NEW) || term.getStatus().equals(TermStatus.CHECKING))
                .collect(Collectors.toList());
    }

    @GetMapping("/lector/terms/de")
    public List<Term> getLectorsDeutschTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, Lang.DE)
                .stream()
                .filter(term -> term.getStatus().equals(TermStatus.NEW) || term.getStatus().equals(TermStatus.CHECKING))
                .collect(Collectors.toList());
    }

    @PostMapping("/lector/termgroup")
    public List<Term> saveTermGroup(@RequestBody Map<String, String> params, Principal principal){
        var termGroup = new TermGroup();
        termService.saveTermGroup(termGroup);
        var ruTerm = new Term.Builder(termGroup.getId(), Lang.RU, TermStatus.NEW, params.get("ruTermName")).build();
        var deTerm = new Term.Builder(termGroup.getId(), Lang.DE, TermStatus.NEW, params.get("deTermName")).build();
        termService.saveTerm(ruTerm);
        termService.saveTerm(deTerm);
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        profileService.addTerm(profile, ruTerm);
        profileService.addTerm(profile, deTerm);
        return List.of(deTerm, ruTerm);
    }

    @GetMapping("/student/terms/all")
    public List<Term> getAllTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.PROCESSING);
    }

    @GetMapping("/student/terms/ru")
    public List<Term> getStudentsRussianTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.PROCESSING, Lang.RU);
    }

    @GetMapping("/student/terms/de")
    public List<Term> getStudentsDeutschTerms(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return termService.getTerms(profile, TermStatus.PROCESSING, Lang.DE);
    }

    @GetMapping("/student/groups")
    public List<ProfileGroup> getStudentProfileGroups(Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        return profileGroupService.getStudentProfileGroups(profile);
    }

    @PostMapping("/student/group/leave/{groupId}")
    public void leaveProfileGroup(@PathVariable Integer groupId, Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        profileGroupService.removeProfile(profile.getId(), groupId);
    }

    @PostMapping("/student/join-group")
    public ProfileGroup joinGroup(@RequestBody Map<String, String> params, Principal principal){
        var profile = profileService.getProfile(principal).orElseGet(Profile::new);
        try {
            return profileGroupService.addProfile(profile, params.get("token"));
        }catch (ProfileGroupNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bad token");
        }catch (AddProfileToProfileGroupException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Group already exists");
        }
    }
}
//class
