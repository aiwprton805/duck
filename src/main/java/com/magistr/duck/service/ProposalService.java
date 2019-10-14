package com.magistr.duck.service;

import com.magistr.duck.entity.Proposal;

import java.util.List;
import java.util.Optional;

public interface ProposalService {

    Optional<Proposal> getProposal(Integer id);

    Optional<Proposal> getProposal(Proposal proposal);

    void save(Proposal proposal);

    void remove(Integer id);

    void remove(Proposal proposal);

    List<Proposal> getProposalsBySubmitterName(String name);

    List<Proposal> getProposalsByTermName(String termName);

    List<Proposal> getProposals();
}
