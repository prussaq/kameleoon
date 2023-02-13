package com.example.kameleoon.service;

import com.example.kameleoon.dto.UserDto;
import com.example.kameleoon.entity.User;
import com.example.kameleoon.exception.UserAlreadyExistException;
import com.example.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public Long create(UserDto userDto) {
        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistException(String.format("user with email '%s' already exists", userDto.getEmail()));
        }
        User user = mapper.map(userDto, User.class);
        return userRepository.save(user).getId();
    }
}
