package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.exception.ResourceNotFoundException;
import com.jpcchaves.finances.domain.model.CostCenter;
import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.domain.repository.CostCenterRepository;
import com.jpcchaves.finances.dto.costcenter.CostCenterRequestDto;
import com.jpcchaves.finances.dto.costcenter.CostCenterResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostCenterService implements ICRUDService<CostCenterRequestDto, CostCenterResponseDto> {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CostCenterResponseDto> findAll() {
        List<CostCenter> costCenterList = costCenterRepository.findAll();

        return costCenterList
                .stream()
                .map(costCenter -> mapper.map(costCenter, CostCenterResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CostCenterResponseDto findById(Long id) {
        var entity = costCenterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um centro de custo com o id informado: " + id));

        return mapper.map(entity, CostCenterResponseDto.class);
    }

    @Override
    public CostCenterResponseDto create(CostCenterRequestDto dto) {
        var costCenter = mapper.map(dto, CostCenter.class);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        costCenter.setUser(user);
        costCenter.setId(null);

        costCenter = costCenterRepository.save(costCenter);

        return mapper.map(costCenter, CostCenterResponseDto.class);
    }

    @Override
    public CostCenterResponseDto update(Long id, CostCenterRequestDto dto) {

        findById(id);

        var costCenter = mapper.map(dto, CostCenter.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        costCenter.setUser(user);
        costCenter.setId(id);

        costCenter = costCenterRepository.save(costCenter);

        return mapper.map(costCenter, CostCenterResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        costCenterRepository.deleteById(id);
    }
}
