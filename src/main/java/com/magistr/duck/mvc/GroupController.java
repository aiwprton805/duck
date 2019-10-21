package com.magistr.duck.mvc;

import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.ProfileGroup;
import com.magistr.duck.service.ProfileGroupService;
import com.magistr.duck.service.ProfileService;
import com.magistr.duck.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group/{groupId}")
public class GroupController {

    private final ProfileGroupService groupService;
    private final ProfileService profileService;

    @Autowired
    public GroupController(ProfileGroupService groupService, ProfileService profileService) {
        this.groupService = groupService;
        this.profileService = profileService;
    }

    @GetMapping("")
    public String group(@PathVariable Integer groupId, Model model){
        var profileGroup = groupService.getProfileGroup(groupId).orElseGet(ProfileGroup::new);
        var lectors = profileService.getLectorProfiles(profileGroup);

        var lectorsId = lectors.stream().map(Profile::getId).collect(Collectors.toList());
        var students = profileGroup.getProfiles().stream().filter(profile -> !lectorsId.contains(profile.getId())).collect(Collectors.toList());

        model.addAllAttributes(Map.of("profileGroup", profileGroup, "lectors", lectors, "students", students));
        return "group/group";
    }
}
