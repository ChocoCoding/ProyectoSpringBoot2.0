package com.example.reservas.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "reserva")
@EqualsAndHashCode(exclude = {"habitacionReservada"})
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private int reserva_id;

    @NonNull
    @Column(name = "usuario_id")
    private int usuario_id;

    @NonNull
    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;

    @NonNull
    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @NonNull
    @Column(name = "estado")
    private String estado;


    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacionReservada;


    public void addHabitacion(Habitacion habitacion){
        this.habitacionReservada = habitacion;
        habitacionReservada.addReserva(this);
    }

}
