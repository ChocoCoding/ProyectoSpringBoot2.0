package com.example.reservas.DTO.HotelDTO;

import com.example.reservas.entities.Habitacion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CrearHotelDTO {

    private String nombre;
    private String direccion;
    private String nombreUsu;
    private String contrasena;

    public CrearHotelDTO(String nombre, String direccion, String nombreUsu, String contrasena) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreUsu = nombreUsu;
        this.contrasena = contrasena;
    }
}
