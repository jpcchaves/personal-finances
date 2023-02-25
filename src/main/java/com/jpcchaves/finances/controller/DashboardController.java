package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.domain.service.DashboardService;
import com.jpcchaves.finances.dto.dashboard.DashboardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    @Operation(summary = "Returns a dashboard", description = "Returns the amountToPay, amountToReceive, balance, payingTitles and receivingTitles into an period of time informed by the user",
            tags = {"Dashboard"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = DashboardResponseDto.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<DashboardResponseDto> getCashFlow(@RequestParam(name = "initialDate") String initialDate, @RequestParam(name = "finalDate") String finalDate) {
        return ResponseEntity.ok(dashboardService.getCashFlow(initialDate, finalDate));
    }
}
