package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UsuarioContrasenhaDTO {

    private String nombre;
    private String contrasena;

    public UsuarioContrasenhaDTO(String nombre) {
        this.nombre = nombre;
    }
}
