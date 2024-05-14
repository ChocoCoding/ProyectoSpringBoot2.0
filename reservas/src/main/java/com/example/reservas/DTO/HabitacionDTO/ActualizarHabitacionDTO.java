package com.example.reservas.DTO.HabitacionDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ActualizarHabitacionDTO {
    private Integer id;
    private String nombre;
    private String contrasena;
    private int numero_habitacion;
    private String tipo;
    private BigDecimal precio;
    private Integer hotel_id;
    private Boolean disponible;

    public ActualizarHabitacionDTO(Integer id, String nombre, String contrasena, int numero_habitacion, String tipo, BigDecimal precio, Integer hotel_id, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numero_habitacion = numero_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.hotel_id = hotel_id;
        this.disponible = disponible;
    }
}
