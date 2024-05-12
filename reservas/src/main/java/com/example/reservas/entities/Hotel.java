package com.example.reservas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "hotel")
@EqualsAndHashCode(exclude = {"habitaciones"})
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int hotel_id;

    @NonNull
    @Column(name = "nombre",length = 100)
    private String nombre;

    @NonNull
    @Column(name = "direccion")
    private String direccion;


    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private List<Habitacion> habitaciones = new ArrayList<>();


    public void addHabitacion(Habitacion habitacion){
        this.habitaciones.add(habitacion);
        habitacion.setHotel(this);
    }


}
