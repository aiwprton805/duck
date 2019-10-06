package com.magistr.duck.service.impl;

import com.magistr.duck.dao.ProposalDao;
import com.magistr.duck.entity.Proposal;
import com.magistr.duck.service.ProposalService;
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

    @Autowired
    public ProposalServiceImpl(ProposalDao proposalDao){
        this.proposalDao = proposalDao;
    }

    @Override
    public Optional<Proposal> getProposal(Integer id) {
        return proposalDao.read(id);
    }

    @Override
    public Optional<Proposal> getProposal(Proposal proposal) {
        if(proposal == null || proposal.getId() == null){
            LOGGER.warn("ProposalServiceImpl.getProposal invalid proposal");
            throw new IllegalArgumentException("proposal and proposal.id must be not null");
        }
        return proposalDao.read(proposal.getId());
    }

    @Override
    public void save(Proposal proposal) {
        if(proposal.getId() == null){
            proposalDao.create(proposal);
        }else{
            proposalDao.update(proposal);
        }
    }

    @Override
    public void remove(Integer id) {
        proposalDao.delete(id);
    }

    @Override
    public void remove(Proposal proposal) {
        if(proposal == null || proposal.getId() == null){
            LOGGER.warn("ProposalServiceImpl.remove invalid proposal");
            throw new IllegalArgumentException("proposal and proposal.id must be not null");
        }
        proposalDao.delete(proposal.getId());
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
    public List<Proposal> getProposals() {
        return proposalDao.findAll();
    }
}
//class
