package com.example.kameleoon.repository;

import com.example.kameleoon.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("select q from Quote q order by RAND() limit 1")
    Optional<Quote> getRandomQuote();

    @Query(value = "SELECT q.id, q.content, q.date, q.user_id, q.vote_id " +
            "FROM quotes q " +
            "JOIN votes v on q.vote_id=v.id " +
            "ORDER BY v.upvote DESC LIMIT :count", nativeQuery = true)
    List<Quote> findTopCountOrderByUpvote(int count);

    @Query(value = "SELECT q.id, q.content, q.date, q.user_id, q.vote_id " +
            "FROM quotes q " +
            "JOIN votes v on q.vote_id=v.id " +
            "ORDER BY v.downvote DESC LIMIT :count", nativeQuery = true)
    List<Quote> findWorseCountOrderByDownvote(int count);

}
