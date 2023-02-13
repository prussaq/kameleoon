package com.example.kameleoon.service;

import com.example.kameleoon.dto.VoteDto;
import com.example.kameleoon.entity.Vote;
import com.example.kameleoon.repository.QuoteRepository;
import com.example.kameleoon.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;
    private final ModelMapper mapper;

    public VoteDto getVote(Long id) {
        if (id != null) {
            return voteRepository.findById(id).map(vote -> mapper.map(vote, VoteDto.class)).orElse(null);
        } else {
            return null;
        }
    }

    public void deleteVote(Long id) {
        try {
            voteRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    @Transactional
    public void upvote(Long quoteId) {
        quoteRepository.findById(quoteId).ifPresent(quote -> {
            if (quote.getVoteId() == null) {
                Vote vote = Vote.builder()
                        .upvote(1L)
                        .build();
                Long voteId = voteRepository.save(vote).getId();
                quote.setVoteId(voteId);
                quoteRepository.save(quote);
            } else {
                voteRepository.upvote(quote.getVoteId());
            }
        });
    }

    @Transactional
    public void downvote(Long quoteId) {
        quoteRepository.findById(quoteId).ifPresent(quote -> {
            if (quote.getVoteId() == null) {
                Vote vote = Vote.builder()
                        .downvote(1L)
                        .build();
                Long voteId = voteRepository.save(vote).getId();
                quote.setVoteId(voteId);
                quoteRepository.save(quote);
            } else {
                voteRepository.downvote(quote.getVoteId());
            }
        });
    }
}
