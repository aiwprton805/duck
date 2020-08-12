package com.magistr.duck.mvc;

import com.magistr.duck.common.enums.ProposalStatus;
import com.magistr.duck.common.enums.Sex;
import com.magistr.duck.entity.Proposal;
import com.magistr.duck.service.FileStorageService;
import com.magistr.duck.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;

import static com.magistr.duck.common.enums.ContentType.*;

@Controller
@RequestMapping("/proposal")
public class ProposalController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProposalController.class);

    private final ProposalService proposalService;

    private final FileStorageService fileStorageService;

    @Autowired
    public ProposalController(ProposalService proposalService, FileStorageService fileStorageService) {
        this.proposalService = proposalService;
        this.fileStorageService = fileStorageService;
    }

    @ModelAttribute("proposals")
    public List<Proposal> populateProposals() {
        return proposalService.getProposals();
    }

    @GetMapping("/add")
    public String add() {
        return "proposal/add";
    }

    @GetMapping("{proposalId}")
    public String proposal(@PathVariable Integer proposalId, Model model) {
        var proposal = proposalService.getProposal(proposalId).orElseGet(Proposal::new);
        model.addAttribute("proposal", proposal);
        return "proposal/proposal";
    }

    @GetMapping("/proposals")
    public String proposals() {
        return "proposal/proposals";
    }

    @PostMapping("/save")
    public String saveProposal(@RequestParam("term") String term, @RequestParam("problem") String problem,
                               @RequestParam("contextFiles") List<MultipartFile> contextFiles,
                               @RequestParam("contextUrl") String contextUrl,
                               @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                               @RequestParam("otherFiles") List<MultipartFile> otherFiles,
                               @RequestParam("sex") String sex, @RequestParam("nickname") String nickname,
                               @RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName,
                               @RequestParam("patronymic") String patronymic, @RequestParam("mail") String mail,
                               @RequestParam(name = "agree", required = false) String agree) throws IOException {
        Sex sexEnum;
        if (sex.equals("M")) {
            sexEnum = Sex.MALE;
        } else if (sex.equals("F")) {
            sexEnum = Sex.FEMALE;
        } else {
            sexEnum = null;
        }
        ///////NOOOOOOOOOOOOOOOOOOOOOOOOO
        if (agree.equals("on")) {
            System.out.println("User Agree!");
        }
        var proposal = new Proposal(null, ProposalStatus.NEW, lastName, firstName, patronymic, nickname, sexEnum, mail, term, problem,
                null, contextUrl, null, null, OffsetDateTime.now());
        proposalService.save(proposal);
        Path contextPath = fileStorageService.saveOnFS(contextFiles, PROPOSAL_CONTEXT, proposal.getId());
        Path imagePath = fileStorageService.saveOnFS(imageFiles, PROPOSAL_IMG, proposal.getId());
        Path otherPath = fileStorageService.saveOnFS(otherFiles, PROPOSAL_OTHER_DOCS, proposal.getId());
        proposal.setContextPath(contextPath.toString());
        proposal.setImagesPath(imagePath.toString());
        proposal.setOtherDocsPath(otherPath.toString());
        proposalService.save(proposal);

        return "redirect:/proposal/add";
    }
}
//class
