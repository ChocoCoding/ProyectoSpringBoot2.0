package com.example.reservas.repositories;

import com.example.reservas.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    @Query("select h.hotel_id from Hotel h where h.nombre=:nombre")
    Integer obtenerIdApartirNombre(@Param("nombre") String nombre);

    @Query("select h.nombre from Hotel h where h.hotel_id=:hotel_id")
    String obtenerNombreAPartirId(@Param("hotel_id") Integer hotel_id);
}
