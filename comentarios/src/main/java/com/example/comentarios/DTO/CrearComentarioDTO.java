package com.example.comentarios.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CrearComentarioDTO {
    private String nombre;
    private String contrasena;
    private String nombreHotel;
    private Integer reservaId;
    private Float puntuacion;
    private String comentario;

    public CrearComentarioDTO(String nombre,String contrasena, String nombreHotel, Integer reservaId, Float puntuacion, String comentario) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.nombreHotel = nombreHotel;
        this.reservaId = reservaId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }
}
