package com.example.comentarios.repository;

import com.example.comentarios.entities.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ComentariosRepository extends MongoRepository<Comentarios,String> {
    List<Comentarios> findByHotelId(Integer hotelId);

}
