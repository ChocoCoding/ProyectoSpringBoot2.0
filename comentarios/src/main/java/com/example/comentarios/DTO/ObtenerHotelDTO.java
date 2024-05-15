package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor

public class ObtenerHotelDTO{
    private String nombreHotel;
    private String nombre;
    private String contrasena;


    public ObtenerHotelDTO(String nombreHotel, String nombre, String contrasena) {
        this.nombreHotel = nombreHotel;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
