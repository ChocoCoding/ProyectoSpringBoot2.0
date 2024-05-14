package com.example.reservas.DTO.HabitacionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CrearHabitacionDTO {
    private String nombre;
    private String contrasena;
    private int numero_habitacion;
    private String tipo;
    private BigDecimal precio;
    private Integer hotel_id;

    public CrearHabitacionDTO(String nombre, String contrasena, int numero_habitacion, String tipo, BigDecimal precio, Integer hotel_id) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numero_habitacion = numero_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.hotel_id = hotel_id;
    }
}
