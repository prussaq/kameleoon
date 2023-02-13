package com.example.kameleoon.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {

    @Nullable
    private Long id;

    @NotBlank
    private String content;

    @Nullable
    private LocalDateTime date = LocalDateTime.now();

    @NotNull
    private Long userId;

    @Nullable
    private String userName;

    @Nullable
    private VoteDto voteDto;

}
