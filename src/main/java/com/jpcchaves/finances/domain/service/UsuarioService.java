package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.domain.repository.UsuarioRepository;
import com.jpcchaves.finances.dto.usuario.UsuarioRequestDto;
import com.jpcchaves.finances.dto.usuario.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioService implements ICRUDService<UsuarioRequestDto, UsuarioResponseDto> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<UsuarioResponseDto> obterTodos() {

        List<User> users = usuarioRepository.findAll();

        return users
                .stream()
                .map(user -> mapper.map(user, UsuarioResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDto obterPorId(Long id) {
        Optional<User> optUsuario = usuarioRepository.findById(id);

        if(optUsuario.isEmpty()){
            //throw
            return null;
        }

        return mapper.map(optUsuario.get(), UsuarioResponseDto.class);

    }

    @Override
    public UsuarioResponseDto cadastrar(UsuarioRequestDto dto) {
        return null;
    }

    @Override
    public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto dto) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
