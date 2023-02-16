package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.domain.repository.UserRepository;
import com.jpcchaves.finances.dto.user.UserRequestDto;
import com.jpcchaves.finances.dto.user.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements ICRUDService<UserRequestDto, UserResponseDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<UserResponseDto> findAll() {

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> optUsuario = userRepository.findById(id);

        if(optUsuario.isEmpty()){
            //throw
            return null;
        }

        return mapper.map(optUsuario.get(), UserResponseDto.class);

    }

    @Override
    public UserResponseDto create(UserRequestDto dto) {
        return null;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
