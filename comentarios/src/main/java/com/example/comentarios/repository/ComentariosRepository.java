package com.example.comentarios.repository;

import com.example.comentarios.entities.Comentarios;
import org.bson.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ComentariosRepository extends MongoRepository<Comentarios,String> {
    List<Comentarios> findByHotelId(Integer hotelId);

    List<Comentarios> findByUsuarioId(Integer usuarioId);

    List<Comentarios> findByUsuarioIdAndReservaId(Integer usuarioId,Integer reservaId);

    @Aggregation(pipeline = {"{$match: {hotelId: ?0}}","{$group: {_id: null, mediaPuntos: {$avg:\"$puntuacion\"}}}"})
    AggregationResults<Document> puntuacionMediaHotel(int hotelId);

    @Aggregation(pipeline = {"{$match: {usuarioId: ?0}}","{$group: {_id: null, mediaPuntos: {$avg:\"$puntuacion\"}}}"})
    AggregationResults<Document> puntuacionesMediasUsuario(int usuarioId);
}
