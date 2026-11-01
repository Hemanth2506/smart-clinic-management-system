package com.smartclinic.service;
import jakarta.validation.constraints.NotBlank;
public record Login(@NotBlank String email, @NotBlank String password) {}
