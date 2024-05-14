package com.example.comentarios.Controller;

import com.example.comentarios.DTO.*;
import com.example.comentarios.entities.Comentarios;
import com.example.comentarios.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

        if (validarUsuario((new UsuarioContrasenhaDTO(crearComentarioDTO.getNombre(),crearComentarioDTO.getContrasena()))) && (checkReserva(new CheckReservaDTO(usuarioId,hotelId,crearComentarioDTO.getReservaId())))){
            comentariosService.crearComentario(crearComentarioDTO,hotelId,usuarioId);
            return crearComentarioDTO;
        }else return null;

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
        return  null;
    }

    @QueryMapping
    public List<ListarComentariosHotelDTO> mostrarComentarioUsuarioReserva(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO,@Argument Integer reservaId){
        if (validarUsuario(usuarioContrasenhaDTO)){
            Integer usuarioId = Integer.valueOf(obtenerIdUsuario(usuarioContrasenhaDTO));
            List<ListarComentariosHotelDTO> listaComentariosDto = comentariosService.mostrarComentarioUsuarioReserva(usuarioId,reservaId).stream()
                    .map(ListarComentariosHotelDTO::new).toList();

            for (ListarComentariosHotelDTO l : listaComentariosDto) {
                String nombreH = obtenerNombreApartirId(usuarioContrasenhaDTO,l.getIdHotel());
                l.setNombreHotel(nombreH);
            }
            return listaComentariosDto;
        }else return null;
    }

    @QueryMapping
    public Double puntuacionesMediasUsuario(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if(validarUsuario(usuarioContrasenhaDTO)){
            int idUsuario = Integer.parseInt(obtenerIdUsuario(usuarioContrasenhaDTO));
            return comentariosService.mediaPuntuacionPorUsuario(idUsuario);
        }else return null;
    }



    //TODO Deberia devolver un DOUBLE
    @QueryMapping
    public Double puntuacionMediaHotel(@Argument UsuarioContrasenhaDTO usuarioContrasenhaDTO,@Argument String nombreHotel){
        if (validarUsuario(usuarioContrasenhaDTO)){
            Integer hotelId = obtenerIdApartirNombre(nombreHotel,usuarioContrasenhaDTO);
            return comentariosService.puntuacionMediaHotel(hotelId);
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

    public String obtenerNombreApartirId(UsuarioContrasenhaDTO usuarioContrasenhaDTO,Integer idHotel){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLRESERVAS + "/hotel/nombre/{idHotel}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idHotel", idHotel);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(urlObtenerInfo, usuarioContrasenhaDTO, String.class, uriVariables);

        return responseEntity.getBody();
    }




    public boolean checkReserva(CheckReservaDTO checkReservaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlCheckReservas = URLRESERVAS + "/check/{idUsuario}-{idHotel}-{idReserva}";
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idUsuario", checkReservaDTO.getUsuarioId());
        uriVariables.put("idHotel", checkReservaDTO.getHotelId());
        uriVariables.put("idReserva", checkReservaDTO.getReservaId());

        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(urlCheckReservas, Boolean.class,checkReservaDTO.getUsuarioId(),checkReservaDTO.getHotelId(),checkReservaDTO.getReservaId());
        return Boolean.TRUE.equals(responseEntity.getBody());
    }



}
