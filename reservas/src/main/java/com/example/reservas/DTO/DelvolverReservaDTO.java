package com.example.reservas.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class DelvolverReservaDTO {

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Integer habitacion_id;

    public DelvolverReservaDTO(LocalDate fecha_inicio, LocalDate fecha_fin, Integer habitacion_id) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.habitacion_id = habitacion_id;
    }
}
