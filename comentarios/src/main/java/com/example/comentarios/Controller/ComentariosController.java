package com.example.comentarios.Controller;

import com.example.comentarios.DTO.*;
import com.example.comentarios.entities.Comentarios;
import com.example.comentarios.service.ComentariosService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ComentariosController {

    private final String URLUSUARIO = "http://localhost:8702/usuarios";
    private final String URLRESERVAS = "http://localhost:8701/reservas";

    @Autowired
    private ComentariosService comentariosService;

    @MutationMapping
    public CrearComentarioDTO crearComentario(@Argument CrearComentarioDTO crearComentarioDTO){
        Integer hotelId = obtenerIdApartirNombre(crearComentarioDTO.getNombreHotel(),new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre(),crearComentarioDTO.getContrasena()));
        Integer usuarioId = Integer.parseInt(obtenerIdUsuario(new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre())));
        Boolean checkIdReserva = checkReserva(new CheckReservaDTO(usuarioId,hotelId,Integer.parseInt(crearComentarioDTO.getReservaId())));
        comentariosService.crearComentario(crearComentarioDTO,hotelId,usuarioId,checkIdReserva);
        return crearComentarioDTO;
    }

    @MutationMapping
    public String eliminarComentarios(){
       return comentariosService.eliminarComentarios();

    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO,@Argument String _id) {
        if (validarUsuario(usuarioContrasenhaDTO)){
            return comentariosService.eliminarComentarioDeUsuario(_id);
        }else return "Error al autenticarse";

    }

    @QueryMapping
    private List<ListarComentariosHotelDTO> listarComentariosHotel(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO,@Argument String nombreHotel){
        if (validarUsuario(usuarioContrasenhaDTO)){
            Integer hotelId = obtenerIdApartirNombre(nombreHotel,usuarioContrasenhaDTO);
            List<ListarComentariosHotelDTO> listaComentariosDTO = comentariosService.listarComentariosHotel(hotelId).stream()
                    .map(ListarComentariosHotelDTO::new).toList();

            for (ListarComentariosHotelDTO l : listaComentariosDTO) {
                l.setNombreHotel(nombreHotel);
            }
            return listaComentariosDTO;
        }else return null;
    }


    public boolean validarUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlValidar = URLUSUARIO + "/validar";
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlValidar, usuarioContrasenhaDTO,Boolean.class);
        return Boolean.TRUE.equals(responseEntity.getBody());
    }


    public String obtenerIdUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLUSUARIO + "/info/nombre/{nombre}";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlObtenerInfo,String.class,usuarioContrasenhaDTO.getNombre());
        return responseEntity.getBody();
    }

    public Integer obtenerIdApartirNombre(String nombreHotel, UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLRESERVAS + "/hotel/id/{nombre}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nombre", nombreHotel);

        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(urlObtenerInfo, usuarioContrasenhaDTO, Integer.class, uriVariables);

        return responseEntity.getBody();
    }

    public boolean checkReserva(CheckReservaDTO checkReservaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlCheckReservas = URLRESERVAS + "/check/{idUsuario}-{idHotel}-{idReserva}";
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(urlCheckReservas, Boolean.class,checkReservaDTO.getUsuarioId()+"-"+checkReservaDTO.getHotelId()+"-"+checkReservaDTO.getReservaId());
        return Boolean.TRUE.equals(responseEntity.getBody());
    }

}
