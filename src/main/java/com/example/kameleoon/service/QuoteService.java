package com.example.kameleoon.service;

import com.example.kameleoon.dto.QuoteDto;
import com.example.kameleoon.entity.Quote;
import com.example.kameleoon.exception.IllegalQuoteStateException;
import com.example.kameleoon.exception.QuoteNotFoundException;
import com.example.kameleoon.repository.QuoteRepository;
import com.example.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final VoteService voteService;
    private final ModelMapper mapper;

    public QuoteDto getRandomQuote() {
        return quoteRepository.getRandomQuote().map(quote -> {
            QuoteDto quoteDto = mapper.map(quote, QuoteDto.class);
            quoteDto.setUserName(userRepository.getNameById(quote.getUserId()));
            quoteDto.setVoteDto(voteService.getVote(quote.getVoteId()));
            return quoteDto;
        }).orElse(null);
    }

    public QuoteDto findQuote(Long id) {
        return quoteRepository.findById(id).map(quote -> {
            QuoteDto quoteDto = mapper.map(quote, QuoteDto.class);
            quoteDto.setUserName(userRepository.getNameById(quote.getUserId()));
            quoteDto.setVoteDto(voteService.getVote(quote.getVoteId()));
            return quoteDto;
        }).orElseThrow(() -> new QuoteNotFoundException("quote not found with id=" + id));
    }

    public Long createQuote(QuoteDto quoteDto) {
        if (quoteDto.getId() != null) {
            throw new IllegalQuoteStateException("quote id must be null");
        }
        return quoteRepository.save(mapper.map(quoteDto, Quote.class)).getId();
    }

    public Long editQuote(QuoteDto quoteDto) {
        if (quoteDto.getId() == null) {
            throw new IllegalQuoteStateException("quote id is null");
        }
        System.out.println("editing");
        return quoteRepository.save(mapper.map(quoteDto, Quote.class)).getId();
    }

    @Transactional
    public void deleteQuote(Long id) {
        if (id == null) {
            throw new IllegalQuoteStateException("quote id is null");
        }
        quoteRepository.findById(id).ifPresent(quote -> {
            voteService.deleteVote(quote.getVoteId());
            quoteRepository.delete(quote);
        });
    }

    public List<QuoteDto> getTopQuotes(int count) {
        return quoteRepository.findTopCountOrderByUpvote(count).stream()
                .map(quote -> {
                    QuoteDto quoteDto = mapper.map(quote, QuoteDto.class);
                    quoteDto.setUserName(userRepository.getNameById(quote.getUserId()));
                    quoteDto.setVoteDto(voteService.getVote(quote.getVoteId()));
                    return quoteDto;
                })
                .collect(Collectors.toList());
    }

    public List<QuoteDto> getWorseQuotes(int count) {
        return quoteRepository.findWorseCountOrderByDownvote(count).stream()
                .map(quote -> {
                    QuoteDto quoteDto = mapper.map(quote, QuoteDto.class);
                    quoteDto.setUserName(userRepository.getNameById(quote.getUserId()));
                    quoteDto.setVoteDto(voteService.getVote(quote.getVoteId()));
                    return quoteDto;
                })
                .collect(Collectors.toList());
    }

}
