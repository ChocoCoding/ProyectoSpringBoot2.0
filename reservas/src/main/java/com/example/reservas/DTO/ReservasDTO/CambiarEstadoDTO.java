package com.example.reservas.DTO.ReservasDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CambiarEstadoDTO {
    private int reserva_id;
    private String estado;
    private String nombre;
    private String contrasena;

    public CambiarEstadoDTO(int reserva_id, String estado, String nombre, String contrasena) {
        this.reserva_id = reserva_id;
        this.estado = estado;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
