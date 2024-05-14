package com.example.usuarios.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActualizarUsuarioDTO {
    private Integer id;
    private String nombre;
    private String correo_electronico;
    private String direccion;
    private String contrasena;

    public ActualizarUsuarioDTO(Integer id, String nombre, String correo_electronico, String direccion, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }
}
