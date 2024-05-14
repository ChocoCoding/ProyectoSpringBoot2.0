package com.example.reservas.DTO.HabitacionDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HabitacionDTO {
    private int habitacion_id;
    private int numero_habitacion;
    private String tipo;
    private BigDecimal precio;
    private Boolean disponible;
    private Integer hotel_id;
    private String nombre;
    private String contrasena;

    public HabitacionDTO() {
    }

    public HabitacionDTO(String nombre,String contrasena,int numero_habitacion, String tipo, BigDecimal precio, Integer hotel_id) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numero_habitacion = numero_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.hotel_id = hotel_id;
    }

    public HabitacionDTO(String nombre,String contrasena,int habitacion_id,int numero_habitacion, String tipo, BigDecimal precio, Integer hotel_id,Boolean disponible) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.habitacion_id = habitacion_id;
        this.numero_habitacion = numero_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.hotel_id = hotel_id;
        this.disponible = disponible;
    }


}
