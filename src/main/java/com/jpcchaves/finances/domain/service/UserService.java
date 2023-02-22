package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.exception.ResourceBadRequestException;
import com.jpcchaves.finances.domain.exception.ResourceNotFoundException;
import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.domain.repository.UserRepository;
import com.jpcchaves.finances.dto.user.UserRequestDto;
import com.jpcchaves.finances.dto.user.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements ICRUDService<UserRequestDto, UserResponseDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id));

        return mapper.map(entity, UserResponseDto.class);
    }

    public UserResponseDto findByEmail(String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o email: " + email);
        }

        return mapper.map(optionalUser.get(), UserResponseDto.class);
    }

    @Override
    public UserResponseDto create(UserRequestDto dto) {

        var entity = userRepository.findByEmail(dto.getEmail());

        if (entity != null) {
            throw new ResourceBadRequestException("Já existe uma conta criada com o e-mail informado: " + dto.getEmail());
        }

        User user = mapper.map(dto, User.class);

        // encryption
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        user.setId(null);
        user.setCreatedAt(new Date());
        userRepository.save(user);

        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id));

        User user = mapper.map(dto, User.class);

        // encryption
        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        user.setId(id);
        user.setInactivationDate(entity.getInactivationDate());
        user.setCreatedAt(entity.getCreatedAt());

        userRepository.save(user);
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public void delete(Long id) {

        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar o usuário pois o ID informado não exist: " + id));

        var user = mapper.map(entity, User.class);
        user.setInactivationDate(new Date());


        userRepository.save(user);
    }
}
