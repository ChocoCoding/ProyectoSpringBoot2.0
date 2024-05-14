package com.example.comentarios.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EliminarComentarioUsuarioDTO {
    private String nombre;
    private String contrasena;
    private String _id;

    public EliminarComentarioUsuarioDTO(String nombre, String contrasena, String _id) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this._id = _id;
    }
}
