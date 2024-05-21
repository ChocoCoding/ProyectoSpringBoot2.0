package com.example.comentarios.Controller;

import com.example.comentarios.DTO.*;
import com.example.comentarios.entities.Comentarios;
import com.example.comentarios.service.ComentariosService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
        Integer hotelId;
        Integer usuarioId;
        try {
            hotelId= obtenerIdApartirNombre(crearComentarioDTO.getNombreHotel(),new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre(),crearComentarioDTO.getContrasena()));
            usuarioId= Integer.parseInt(obtenerIdUsuario(new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre())));
            if (validarUsuario((new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre(),
                crearComentarioDTO.getContrasena()))) && (checkReserva(new CheckReservaDTO(usuarioId,hotelId,crearComentarioDTO.getReservaId())))){
                if(!comentariosService.comprobarComentarioExiste(usuarioId,hotelId,crearComentarioDTO.getReservaId())){
                comentariosService.crearComentario(crearComentarioDTO,hotelId,usuarioId);
                return crearComentarioDTO;
                }else return null;
            }return null;

        }catch (Exception e){
            return null;
        }



    }

    @MutationMapping
    public String eliminarComentarios(){
        return comentariosService.eliminarComentarios();

    }

    @MutationMapping
    public String eliminarComentarioDeUsuario(@Argument EliminarComentarioUsuarioDTO eliminarComentarioUsuarioDTO) {
        if (validarUsuario(new UsuarioContrasenhaDTO(eliminarComentarioUsuarioDTO.getNombre(),eliminarComentarioUsuarioDTO.getContrasena()))){
            return comentariosService.eliminarComentarioDeUsuario(eliminarComentarioUsuarioDTO.get_id());
        }else return "Error al autenticarse";

    }

    @QueryMapping
    private List<ListarComentariosHotelDTO> listarComentariosHotel(@Argument ListarComentariosHotelDTO listarComentariosHotelDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(listarComentariosHotelDTO.getNombre(),listarComentariosHotelDTO.getContrasena()))){
            Integer hotelId = obtenerIdApartirNombre(listarComentariosHotelDTO.getNombreHotel(),new UsuarioContrasenhaDTO(listarComentariosHotelDTO.getNombre(),listarComentariosHotelDTO.getContrasena()));
            List<ListarComentariosHotelDTO> listaComentariosDTO = comentariosService.listarComentariosHotel(hotelId).stream()
                    .map(ListarComentariosHotelDTO::new).toList();

            for (ListarComentariosHotelDTO l : listaComentariosDTO) {
                l.setNombreHotel(listarComentariosHotelDTO.getNombreHotel());
            }
            return listaComentariosDTO;
        }else return Collections.emptyList();
    }


    @QueryMapping
    List<ListarComentariosHotelDTO> listarComentariosUsuario(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            Integer usuarioId = Integer.valueOf(obtenerIdUsuario(usuarioContrasenhaDTO));

            List<ListarComentariosHotelDTO> listaComentariosDto = comentariosService.listarComentariosUsuario(usuarioId).stream()
                    .map(ListarComentariosHotelDTO::new).toList();

            for (ListarComentariosHotelDTO l : listaComentariosDto) {
                String nombreH = obtenerNombreApartirId(usuarioContrasenhaDTO,l.getIdHotel());
                l.setNombreHotel(nombreH);
            }
            return listaComentariosDto;
        }
        return Collections.emptyList();
    }

    @QueryMapping
    public List<ListarComentariosHotelDTO> mostrarComentarioUsuarioReserva(@Argument MostrarComentarioReservaDTO mostrarComentarioReservaDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(mostrarComentarioReservaDTO.getNombre(),mostrarComentarioReservaDTO.getContrasena()))){
            Integer usuarioId = Integer.valueOf(obtenerIdUsuario(new UsuarioContrasenhaDTO(mostrarComentarioReservaDTO.getNombre(),mostrarComentarioReservaDTO.getContrasena())));
            List<ListarComentariosHotelDTO> listaComentariosDto = comentariosService.mostrarComentarioUsuarioReserva(usuarioId, mostrarComentarioReservaDTO.getReservaId()).stream()
                    .map(ListarComentariosHotelDTO::new).toList();
            for (ListarComentariosHotelDTO l : listaComentariosDto) {
                String nombreH = obtenerNombreApartirId(new UsuarioContrasenhaDTO(mostrarComentarioReservaDTO.getNombre(),mostrarComentarioReservaDTO.getContrasena()),l.getIdHotel());
                l.setNombreHotel(nombreH);
            }
            return listaComentariosDto;
        }else return Collections.emptyList();
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if(validarUsuario(usuarioContrasenhaDTO)){
            int idUsuario = Integer.parseInt(obtenerIdUsuario(usuarioContrasenhaDTO));
            return comentariosService.mediaPuntuacionPorUsuario(idUsuario);
        }else return null;
    }


    @QueryMapping
    public Double puntuacionMediaHotel(@Argument ObtenerHotelDTO obtenerHotelDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(obtenerHotelDTO.getNombre(),obtenerHotelDTO.getContrasena()))){
            try {
                Integer hotelId = obtenerIdApartirNombre(obtenerHotelDTO.getNombreHotel(),new UsuarioContrasenhaDTO(obtenerHotelDTO.getNombre(),obtenerHotelDTO.getContrasena()));
                return comentariosService.puntuacionMediaHotel(hotelId);
            }catch (NullPointerException e){
                return -1.0;
            }
        }else return -1.0;
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
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlObtenerInfo,String.class,usuarioContrasenhaDTO.getNombre());
            return responseEntity.getBody();
        }catch (Exception e){
            return "No se pudo encontrar al usuario";
        }

    }

    public Integer obtenerIdApartirNombre(String nombreHotel, UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLRESERVAS + "/hotel/id/{nombre}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nombre", nombreHotel);
        try {
        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(urlObtenerInfo, usuarioContrasenhaDTO, Integer.class, uriVariables);
        return responseEntity.getBody();
        }catch (NullPointerException e){
            return -1;
        }

    }

    public String obtenerNombreApartirId(UsuarioContrasenhaDTO usuarioContrasenhaDTO,Integer idHotel){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLRESERVAS + "/hotel/nombre/{idHotel}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idHotel", idHotel);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlObtenerInfo, usuarioContrasenhaDTO, String.class, uriVariables);
            return responseEntity.getBody();
        }catch (Exception e){
            return null;
        }

    }


    public boolean checkReserva(CheckReservaDTO checkReservaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlCheckReservas = URLRESERVAS + "/check/{idUsuario}-{idHotel}-{idReserva}";

        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(urlCheckReservas, Boolean.class,checkReservaDTO.getUsuarioId(),checkReservaDTO.getHotelId(),checkReservaDTO.getReservaId());
            return Boolean.TRUE.equals(responseEntity.getBody());
        }catch (Exception e){
            return false;
        }

    }



}
