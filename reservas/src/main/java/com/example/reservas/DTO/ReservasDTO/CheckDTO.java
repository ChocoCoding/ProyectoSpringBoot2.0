package com.example.reservas.DTO.ReservasDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckDTO {

    private Integer idUsuario;
    private Integer idHotel;
    private Integer idReserva;

    public CheckDTO(Integer idUsuario, Integer idHotel, Integer idReserva) {
        this.idUsuario = idUsuario;
        this.idHotel = idHotel;
        this.idReserva = idReserva;
    }
}
