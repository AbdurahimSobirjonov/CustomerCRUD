package com.example.customercrud.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    @NotNull (message = "Ism kiritiladi")
    private String name;
    @NotNull(message = "telefon raqam kiritilmadi")
    private String phone;
    @NotNull(message = "Adres kiritilmadi")
    private String address;
}
