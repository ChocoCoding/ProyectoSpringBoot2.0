package com.example.reservas.repositories;

import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion,Integer> {

    void deleteByHotel(Optional<Hotel> hotel);
}
