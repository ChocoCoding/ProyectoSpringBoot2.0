package com.example.reservas.DTO;

import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Reserva;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {
    private int reserva_id;
    private int usuario_id;
    private Integer habitacion_id;
    private String fecha_inicio;
    private String fecha_fin;
    private String estado;
    private Habitacion habitacionReservada;
    private String nombre;
    private String contrasena;

    public ReservaDTO() {
    }

    public ReservaDTO(String nombre,String contrasena,int reserva_id, String estado) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.reserva_id = reserva_id;
        this.estado = estado;
    }

    public ReservaDTO(String nombre,String contrasena,String fecha_inicio, String fecha_fin, Integer habitacion_id) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.habitacion_id = habitacion_id;
    }



    public ReservaDTO(String nombre,String contrasena,int reserva_id, int usuario_id, Integer habitacion_id, String fecha_inicio, String fecha_fin, String estado) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.reserva_id = reserva_id;
        this.usuario_id = usuario_id;
        this.habitacion_id = habitacion_id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }



    public ReservaDTO(Reserva reserva) {
        this.reserva_id = reserva.getReserva_id();
        this.usuario_id = reserva.getUsuario_id();
        this.habitacion_id = reserva.getHabitacionReservada().getHabitacion_id();
        this.fecha_inicio = reserva.getFecha_inicio().toString();
        this.fecha_fin = reserva.getFecha_fin().toString();
        this.estado = reserva.getEstado();
    }
}
