package com.example.comentarios.service;

import com.example.comentarios.DTO.CrearComentarioDTO;
import com.example.comentarios.entities.Comentarios;
import com.example.comentarios.repository.ComentariosRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    public String eliminarComentarios(){
        List<Comentarios> listaComentarios =  comentariosRepository.findAll();

        try {
            comentariosRepository.deleteAll();
            return "Se han eliminado todos los comentarios";
        }catch (Exception e){return "No se han podido eliminar los comentarios";}
    }

    public String eliminarComentarioDeUsuario(String _id){
        try {
            comentariosRepository.deleteById(_id);
            return "Se ha eliminado el comentario";
        }catch (Exception e){
            return "No se ha podido eliminar el comentario";
        }
    }

    public List<Comentarios> listarComentariosHotel(Integer hotelId){
        return comentariosRepository.findByHotelId(hotelId);
    }

    public void crearComentario(CrearComentarioDTO crearComentarioDTO,Integer hotelId,Integer usuarioId,Boolean checkIdReserva){
        Comentarios comentario = new Comentarios();
        comentario.setUsuarioId(usuarioId);
        comentario.setHotelId(hotelId);
        comentario.setReservaId(Integer.parseInt(crearComentarioDTO.getReservaId()));
        comentario.setPuntuacion(crearComentarioDTO.getPuntuacion());
        comentario.setComentario(crearComentarioDTO.getComentario());
        comentario.setPuntuacion(crearComentarioDTO.getPuntuacion());
        comentariosRepository.save(comentario);
    }
}
