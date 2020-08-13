package com.magistr.duck.service.impl;

import com.magistr.duck.common.enums.ProposalStatus;
import com.magistr.duck.dao.ProposalDao;
import com.magistr.duck.entity.Profile;
import com.magistr.duck.entity.Proposal;
import com.magistr.duck.entity.Role;
import com.magistr.duck.entity.User;
import com.magistr.duck.service.ProfileService;
import com.magistr.duck.service.ProposalService;
import com.magistr.duck.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProposalServiceImpl.class);

    private final ProposalDao proposalDao;
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public ProposalServiceImpl(ProposalDao proposalDao, ProfileService profileService, UserService userService) {
        this.proposalDao = proposalDao;
        this.profileService = profileService;
        this.userService = userService;
    }

    @Override
    public Optional<Proposal> getProposal(Integer id) {
        return proposalDao.read(id);
    }

    @Override
    public Optional<Proposal> getProposal(Proposal proposal) {
        if (proposal == null || proposal.getId() == null) {
            LOGGER.warn("ProposalServiceImpl.getProposal invalid proposal");
            throw new IllegalArgumentException("proposal and proposal.id must be not null");
        }
        return proposalDao.read(proposal.getId());
    }

    @Override
    public void save(Proposal proposal) {
        if (proposal.getId() == null) {
            proposalDao.create(proposal);
        } else {
            proposalDao.update(proposal);
        }
    }

    @Override
    public void remove(Integer id) {
        proposalDao.delete(id);
    }

    @Override
    public void remove(Proposal proposal) {
        if (proposal == null || proposal.getId() == null) {
            LOGGER.warn("ProposalServiceImpl.remove invalid proposal");
            throw new IllegalArgumentException("proposal and proposal.id must be not null");
        }
        proposalDao.delete(proposal.getId());
    }

    @Override
    public void bindProposalToLector(Integer proposalId, Integer lectorId) {
        if (proposalId == null || lectorId == null) {
            LOGGER.warn("ProposalServiceImpl.bindProposalToLector invalid proposalId or lectorId");
            throw new IllegalArgumentException("proposalId and lectorId must be not null");
        }
        var lectorProfile = profileService.getProfile(lectorId).orElseGet(Profile::new);
        var lectorUser = userService.getUser(lectorProfile.getUserId()).orElseGet(User::new);
        if (lectorUser.getRoles().stream().map(Role::getName).anyMatch(role -> role.equals("lector"))) {
            LOGGER.warn("ProposalServiceImpl.bindProposalToLector invalid lectorId");
            throw new IllegalStateException("User with profile_id = " + lectorId + " must have 'lector' role");
        }
        var proposal = proposalDao.read(proposalId).orElseGet(Proposal::new);
        proposal.setStatus(ProposalStatus.ACCEPTED);
        proposal.setLectorId(lectorId);
        proposalDao.update(proposal);
    }

    @Override
    public List<Proposal> getProposalsBySubmitterName(String name) {
        return proposalDao.findByAnyName(name);
    }

    @Override
    public List<Proposal> getProposalsByTermName(String termName) {
        return proposalDao.findByTermName(termName);
    }

    @Override
    public List<Proposal> getProposalsByLector(Profile lector) {
        if (lector == null || lector.getId() == null) {
            LOGGER.warn("ProposalServiceImpl.getProposalsByLector invalid lector");
            throw new IllegalArgumentException("lector and lector.id must be not null");
        }
        return proposalDao.findByLectorId(lector.getId());
    }

    @Override
    public List<Proposal> getProposalsByStatus(ProposalStatus status) {
        if (status == null) {
            LOGGER.warn("ProposalServiceImpl.getProposalsByStatus invalid status");
            throw new IllegalArgumentException("status must be not null");
        }
        return proposalDao.findByStatus(status);
    }

    @Override
    public List<Proposal> getProposals() {
        return proposalDao.findAll();
    }
}
//class
