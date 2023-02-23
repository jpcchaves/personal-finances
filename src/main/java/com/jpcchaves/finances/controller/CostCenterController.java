package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.domain.model.CostCenter;
import com.jpcchaves.finances.domain.service.CostCenterService;
import com.jpcchaves.finances.dto.costcenter.CostCenterRequestDto;
import com.jpcchaves.finances.dto.costcenter.CostCenterResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/costcenters")
public class CostCenterController {

    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    public ResponseEntity<List<CostCenterResponseDto>> findAll() {
        return ResponseEntity
                .ok(costCenterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenterResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok(costCenterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CostCenterResponseDto> create(@Valid @RequestBody CostCenterRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(costCenterService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostCenterResponseDto> update(@PathVariable Long id, @Valid @RequestBody CostCenterRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(costCenterService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        costCenterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
