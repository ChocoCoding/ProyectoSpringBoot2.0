package com.example.comentarios.service;

import com.example.comentarios.DTO.CrearComentarioDTO;
import com.example.comentarios.entities.Comentarios;
import com.example.comentarios.repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    public String eliminarComentarios(){
        try {
        List<Comentarios> listaComentarios =  comentariosRepository.findAll();
            comentariosRepository.deleteAll();
            return "Se han eliminado todos los comentarios";
        }catch (Exception e){return "No se han podido eliminar los comentarios";}
    }

    public String eliminarComentarioDeUsuario(String _id){
        Comentarios existe = comentariosRepository.findById(_id).orElse(null);
        if(existe!= null){
            comentariosRepository.deleteById(_id);
            return "Se ha eliminado el comentario";
        }else return "La id del comentario no existe";
    }

    public List<Comentarios> listarComentariosHotel(Integer hotelId){
        try {
            return comentariosRepository.findByHotelId(hotelId);
        }catch (Exception e){
            return Collections.emptyList();
        }

    }

    public boolean comprobarComentarioExiste(int usuarioId, int hotelId, int reservaId){

        Comentarios comentarios = comentariosRepository.findByUsuarioIdAndHotelIdAndReservaId(usuarioId,hotelId,reservaId);

        if (comentarios!=null){
            return true;
        }else return false;

    }

    public void crearComentario(CrearComentarioDTO crearComentarioDTO,Integer hotelId,Integer usuarioId){
        Comentarios comentario = new Comentarios();
        comentario.setUsuarioId(usuarioId);
        comentario.setHotelId(hotelId);
        comentario.setReservaId(crearComentarioDTO.getReservaId());
        comentario.setPuntuacion(crearComentarioDTO.getPuntuacion());
        comentario.setComentario(crearComentarioDTO.getComentario());
        comentario.setPuntuacion(crearComentarioDTO.getPuntuacion());
        comentariosRepository.save(comentario);
    }


    public List<Comentarios> listarComentariosUsuario(Integer usuarioId){
        return comentariosRepository.findByUsuarioId(usuarioId);
    }

    public List<Comentarios>  mostrarComentarioUsuarioReserva(Integer usuarioId, Integer reservaID){

            return comentariosRepository.findByUsuarioIdAndReservaId(usuarioId,reservaID);


    }

    public Double puntuacionMediaHotel(int idHotel){
        try {
            return comentariosRepository.puntuacionMediaHotel(idHotel).getUniqueMappedResult().getDouble("media");
        }catch (NullPointerException e){
            return -1.0;
        }

    }

    public Double mediaPuntuacionPorUsuario(int idUsuario){
        try {
            return comentariosRepository.puntuacionesMediasUsuario(idUsuario).getUniqueMappedResult().getDouble("media");
        }catch (NullPointerException e){
            return -1.0;
        }
    }

}
