package com.example.comentarios.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "comentarios")
public class Comentarios {

    @Id
    private String _id;
    private Integer usuarioId;
    private Integer hotelId;
    private Integer reservaId;
    private Float puntuacion;
    private String comentario;
    private LocalDateTime fechaCreacion;

}
