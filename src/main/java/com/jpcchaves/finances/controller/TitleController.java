package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.domain.service.TitleService;
import com.jpcchaves.finances.dto.title.TitleRequestDto;
import com.jpcchaves.finances.dto.title.TitleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Finds all titles", description = "Finds all titles related to the current logged user",
            tags = {"Titles"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TitleResponseDto.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<TitleResponseDto>> findAll() {
        return ResponseEntity
                .ok(titleService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a title", description = "Finds a title related to the current logged user",
            tags = {"Titles"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TitleResponseDto.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
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
