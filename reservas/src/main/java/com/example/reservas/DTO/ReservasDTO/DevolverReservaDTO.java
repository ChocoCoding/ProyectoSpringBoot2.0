package com.example.reservas.DTO.ReservasDTO;

import com.example.reservas.entities.Reserva;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DevolverReservaDTO {

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Integer habitacion_id;

    public DevolverReservaDTO(LocalDate fecha_inicio, LocalDate fecha_fin, Integer habitacion_id) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.habitacion_id = habitacion_id;
    }

    public DevolverReservaDTO(Reserva reserva){
        this.setHabitacion_id(reserva.getReserva_id());
        this.setFecha_inicio(reserva.getFecha_inicio());
        this.setFecha_fin(reserva.getFecha_fin());
    }

}
