package com.example.kameleoon.repository;

import com.example.kameleoon.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Modifying
    @Query(value = "update Vote v set v.upvote=v.upvote + 1 where v.id=:voteId")
    void upvote(Long voteId);

    @Modifying
    @Query(value = "update Vote v set v.downvote=v.downvote + 1 where v.id=:voteId")
    void downvote(Long voteId);
}
