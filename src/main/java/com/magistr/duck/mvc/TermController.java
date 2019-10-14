package com.magistr.duck.mvc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.magistr.duck.common.enums.Lang;
import com.magistr.duck.common.utils.Transform;
import com.magistr.duck.entity.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.magistr.duck.entity.TermGroup;
import com.magistr.duck.service.TermService;

@Controller
@RequestMapping("/terms")
public class TermController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TermController.class);

    private final TermService termService;

    @Autowired
    public TermController(TermService termService) {
        this.termService = termService;
    }

    @ModelAttribute("groups")
    public List<TermGroup> populateGroups() {
        return termService.getLastCompletedTermGroups();
    }

    @GetMapping("")
    public String terms() {
        return "term/terms";
    }

    @GetMapping("/term/{groupId}")
    public String term(@PathVariable Integer groupId, Model model){
        var group = termService.getTermGroup(groupId).orElseGet(TermGroup::new);
        var termMap = termService.termGroupToTermMap(group);
        model.addAttribute("group", group);
        model.addAttribute("termMap", termMap);
        return "term/term";
    }

    @ResponseBody
    @GetMapping("/search")
    public List<TermGroup> search(@RequestParam("query") String query, @RequestParam("lang") String lang){
        Lang langEnum = Transform.toLang(lang);
        var term = termService.getTerm(query, langEnum).orElseGet(Term::new);
        var termGroup = termService.getTermGroup(term).orElseGet(TermGroup::new);
        return List.of(termGroup);
    }
}
//class
