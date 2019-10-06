package com.magistr.duck.mvc;

import java.security.Principal;
import java.util.List;

import com.magistr.duck.dto.UserProfile;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.Proposal;
import com.magistr.duck.entity.User;
import com.magistr.duck.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    private final UserService userService;

    private final ProfileService profileService;

    private final ProfileGroupService profileGroupService;

    private final UserProfileService userProfileService;

    private final ProposalService proposalService;

    @Autowired
    public ProfileController(UserService userService, ProfileService profileService,
                             ProfileGroupService profileGroupService, UserProfileService userProfileService,
                             ProposalService proposalService) {
        this.userService = userService;
        this.profileService = profileService;
        this.profileGroupService = profileGroupService;
        this.userProfileService = userProfileService;
        this.proposalService = proposalService;
    }

    @ModelAttribute("user")
    public User populateUser(Principal principal){
        return userService.getUser(principal.getName()).orElseGet(User::new);
    }

    @ModelAttribute("profile")
    public Profile populateProfile(Principal principal){
        return profileService.getProfile(principal).orElseGet(Profile::new);
    }

    @ModelAttribute("proposals")
    public List<Proposal> populateProposals(){
        return proposalService.getProposals();
    }

    @ModelAttribute("lectorUserProfiles")
    public List<UserProfile> populateLectorUserProfiles(){
        return userProfileService.getLectors();
    }

    @ModelAttribute("userProfiles")
    public List<UserProfile> populateUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @GetMapping("")
    public String profile() {
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfile() {
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute Profile profile, Principal principal){
        profileService.saveProfile(profile, principal);
        return "redirect:/profile/edit";
    }
}
