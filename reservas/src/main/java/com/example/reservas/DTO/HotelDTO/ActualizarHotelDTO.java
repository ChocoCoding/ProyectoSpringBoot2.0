package com.example.reservas.DTO.HotelDTO;

import com.example.reservas.entities.Habitacion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ActualizarHotelDTO {
    private int id;
    private String nombre;
    private String direccion;
    private String nombreUsu;
    private String contrasena;

    public ActualizarHotelDTO(int id, String nombre, String direccion, String nombreUsu, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreUsu = nombreUsu;
        this.contrasena = contrasena;
    }
}
