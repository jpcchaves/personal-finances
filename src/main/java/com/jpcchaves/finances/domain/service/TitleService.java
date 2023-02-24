package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.exception.ResourceNotFoundException;
import com.jpcchaves.finances.domain.model.Title;
import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.domain.repository.TitleRepository;
import com.jpcchaves.finances.dto.title.TitleRequestDto;
import com.jpcchaves.finances.dto.title.TitleResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class TitleService implements ICRUDService<TitleRequestDto, TitleResponseDto> {


    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TitleResponseDto> findAll() {
        List<Title> titles = titleRepository.findAll();

        return titles.stream()
                .map(title -> mapper.map(title, TitleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TitleResponseDto findById(Long id) {
        var entity = titleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um título com o id: " + id));

        return mapper.map(entity, TitleResponseDto.class);
    }

    @Override
    public TitleResponseDto create(TitleRequestDto dto) {
        Title title = mapper.map(dto, Title.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        title.setUser(user);
        title.setId(null);

        title = titleRepository.save(title);

        return mapper.map(title, TitleResponseDto.class);
    }

    @Override
    public TitleResponseDto update(Long id, TitleRequestDto dto) {
        findById(id);

        Title title = mapper.map(dto, Title.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        title.setUser(user);
        title.setId(id);

        title = titleRepository.save(title);

        return mapper.map(title, TitleResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        titleRepository.deleteById(id);
    }
}