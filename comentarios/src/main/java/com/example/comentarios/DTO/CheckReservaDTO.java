package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class CheckReservaDTO {

    private Integer usuarioId;
    private Integer hotelId;
    private Integer reservaId;

    public CheckReservaDTO(Integer usuarioId, Integer hotelId, Integer reservaId) {
        this.usuarioId = usuarioId;
        this.hotelId = hotelId;
        this.reservaId = reservaId;
    }
}
