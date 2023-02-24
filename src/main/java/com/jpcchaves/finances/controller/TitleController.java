package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.domain.service.CostCenterService;
import com.jpcchaves.finances.domain.service.TitleService;
import com.jpcchaves.finances.dto.costcenter.CostCenterRequestDto;
import com.jpcchaves.finances.dto.title.TitleRequestDto;
import com.jpcchaves.finances.dto.title.TitleResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleResponseDto>> findAll() {
        return ResponseEntity
                .ok(titleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(titleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TitleResponseDto> create(@Valid @RequestBody TitleRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(titleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleResponseDto> update(@PathVariable Long id, @Valid @RequestBody TitleRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(titleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        titleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
