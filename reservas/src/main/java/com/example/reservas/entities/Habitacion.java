package com.example.reservas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "habitacion")
@EqualsAndHashCode(exclude = {"hotel","reservas"})
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private int habitacion_id;

    @NonNull
    @Column(name = "numero_habitacion")
    private int numero_habitacion;

    @NonNull
    @Column(name = "tipo")
    private String tipo;

    @NonNull
    @Column(name = "precio",precision = 10,scale = 2)
    private BigDecimal precio;

    @NonNull
    @Column(name = "disponible")
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @ToString.Exclude
    private Hotel hotel;

    @OneToMany(mappedBy = "habitacionReservada")
    @ToString.Exclude
    private List<Reserva> reservas;


    public void addHotel(Hotel hotel){
        this.hotel = hotel;
        hotel.addHabitacion(this);
    }

    public void addReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

}
