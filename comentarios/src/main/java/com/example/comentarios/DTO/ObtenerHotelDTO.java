package com.example.comentarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor

public class ObtenerHotelDTO{
    private String nombreHotel;
    private UsuarioContrasenhaDTO usuarioContrasenhaDTO;


    public ObtenerHotelDTO(UsuarioContrasenhaDTO usuarioContrasenhaDTO,String nombreHotel) {
        this.nombreHotel = nombreHotel;
        this.usuarioContrasenhaDTO = usuarioContrasenhaDTO;
    }
}
