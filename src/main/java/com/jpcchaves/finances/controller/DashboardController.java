package com.jpcchaves.finances.controller;

import com.jpcchaves.finances.domain.service.DashboardService;
import com.jpcchaves.finances.dto.dashboard.DashboardResponseDto;
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
    public ResponseEntity<DashboardResponseDto> getCashFlow(@RequestParam(name = "initialDate") String initialDate, @RequestParam(name = "finalDate") String finalDate){
        return ResponseEntity.ok(dashboardService.getCashFlow(initialDate, finalDate));
    }
}
