package com.example.reservas.repositories;

import com.example.reservas.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Integer> {
    @Modifying
    @Query("update Reserva r SET r.estado=:estado WHERE r.reserva_id=:reserva_id")
    void updateEstadoById(@Param("estado") String estado,@Param("reserva_id") Integer reserva_id);


    @Query("select r from Reserva r WHERE r.estado =:estado")
    List<Reserva> listarReservasSegunEstado(@Param("estado") String estado);

    @Query("select r from Reserva r WHERE r.usuario_id =:id")
    List<Reserva> listarReservasUsuario(@Param("id") Integer id);

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.usuario_id = :userId AND r.habitacionReservada.hotel.hotel_id = :hotelId AND r.reserva_id = :reservaId")
    Integer checkReserva(@Param("userId") Integer userId, @Param("hotelId") Integer hotelId, @Param("reservaId") Integer reservaId);


}
