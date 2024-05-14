package com.example.reservas.DTO.HotelDTO;

import com.example.reservas.entities.Habitacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HotelDTO {

    private int hotel_id;
    private String nombre;
    private String direccion;
    private List<Habitacion> habitaciones = new ArrayList<>();
    private String nombreUsu;
    private String contrasena;

    public HotelDTO() {
    }

    public HotelDTO(String nombreUsu,String contrasena,int hotel_id, String nombre, String direccion, List<Habitacion> habitaciones) {
        this.nombreUsu = nombreUsu;
        this.contrasena = contrasena;
        this.hotel_id = hotel_id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.habitaciones = habitaciones;
    }

    public HotelDTO(String nombreUsu,String contrasena,String nombre, String direccion) {
        this.nombreUsu = nombreUsu;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public HotelDTO(String nombreUsu,String contrasena,int hotel_id, String nombre, String direccion) {
        this.nombreUsu = nombreUsu;
        this.contrasena = contrasena;
        this.hotel_id = hotel_id;
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
