package com.jpcchaves.finances.dto.costcenter;

import jakarta.validation.constraints.NotBlank;

public class CostCenterRequestDto {

    private Long id;
    @NotBlank(message = "A descrição é obrigatória!")
    private String description;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
