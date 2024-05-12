package com.example.reservas.DTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsuarioContrasenhaDTO {
    private String nombre;
    private String contrasena;

    public UsuarioContrasenhaDTO(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public UsuarioContrasenhaDTO(String nombre) {
        this.nombre = nombre;
    }
}
