package com.example.kameleoon.controller;

import com.example.kameleoon.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("votes")
public class VoteController {

    private final VoteService voteService;

    @GetMapping("upvote/{quoteId}")
    public void upvote(@PathVariable Long quoteId) {
        voteService.upvote(quoteId);
    }

    @GetMapping("downvote/{quoteId}")
    public void downvote(@PathVariable Long quoteId) {
        voteService.downvote(quoteId);
    }
}
