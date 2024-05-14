package com.example.reservas.DTO.ReservasDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrearReserva {

    private Integer habitacion_id;
    private String fecha_inicio;
    private String fecha_fin;
    private String nombre;
    private String contrasena;
    private String estado;


    public CrearReserva(Integer habitacion_id, String fecha_inicio, String fecha_fin, String nombre, String contrasena) {
        this.estado = "Pendiente";
        this.habitacion_id = habitacion_id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.nombre = nombre;
        this.contrasena = contrasena;

    }
}
