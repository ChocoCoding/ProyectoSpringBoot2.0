package com.example.usuarios.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidarUsuarioDTO {
    private String nombre;
    private String contrasena;
}
