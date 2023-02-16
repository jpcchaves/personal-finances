package com.jpcchaves.finances.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserRequestDto {
    private String name;
    @NotBlank(message = "O email é obrigatório!")
    private String email;
    @NotBlank(message = "A senha é obrigatória!")
    @Length(min = 6, message = "A senha deve ter no mínimo 6 caraceres.")
    @Length(max = 15, message = "A senha deve ter no máximo 15 caraceres.")
    private String password;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
