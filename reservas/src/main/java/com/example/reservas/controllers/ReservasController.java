package com.example.reservas.controllers;

import com.example.reservas.DTO.ReservasDTO.CambiarEstadoDTO;
import com.example.reservas.DTO.ReservasDTO.CrearReserva;
import com.example.reservas.DTO.ReservasDTO.DevolverReservaDTO;
import com.example.reservas.DTO.ReservasDTO.ReservaDTO;
import com.example.reservas.DTO.UsuarioContrasenhaDTO;
import com.example.reservas.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservasController {

    private final String URLUSUARIO = "http://localhost:8702/usuarios";

    @Autowired
    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<String> crearReserva(@RequestBody CrearReserva crearReserva) {
        if (validarUsuario(new UsuarioContrasenhaDTO(crearReserva.getNombre(),crearReserva.getContrasena()))){
            Integer usuarioId = obtenerIdUsuario(new UsuarioContrasenhaDTO(crearReserva.getNombre(),crearReserva.getContrasena()));
                boolean creada = reservaService.guardar(crearReserva,usuarioId);
                if (creada){
                    return ResponseEntity.ok().body("La reserva se ha creado con éxito");
                }else return ResponseEntity.ok().body("La reserva no se ha podido crear");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    @PatchMapping
    public ResponseEntity<String> cambiarEstado(@RequestBody CambiarEstadoDTO cambiarEstadoDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(cambiarEstadoDTO.getNombre(),cambiarEstadoDTO.getContrasena()))){
            boolean cambiado = reservaService.updateEstadoById(cambiarEstadoDTO);
            if (cambiado){
                return ResponseEntity.ok().body("El estado se ha modificado con éxito");
            }else return ResponseEntity.ok().body("No se ha podido realizar la reserva, comprueba el estado introducido");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    @GetMapping
    public ResponseEntity<List<DevolverReservaDTO>> listarReservasUsuario(@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO) {
        Integer idUsuario = obtenerIdUsuario(usuarioContrasenhaDTO);
        if (validarUsuario(usuarioContrasenhaDTO)) {
            List<DevolverReservaDTO> delvolverReservaDTO = reservaService.listarReservasUsuario(idUsuario);
            if (!delvolverReservaDTO.isEmpty()) {
                return ResponseEntity.ok().body(delvolverReservaDTO);
            } else return null;
        } else return null;
    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<DevolverReservaDTO>> listarReservasSegunEstado(@PathVariable String estado,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO) {
        if (validarUsuario(usuarioContrasenhaDTO)) {
            List<DevolverReservaDTO> devolverReservaDTO = reservaService.listarReservasSegunEstado(estado).stream()
                    .map(DevolverReservaDTO::new).toList();

            if (!devolverReservaDTO.isEmpty()) {
                return ResponseEntity.ok().body(devolverReservaDTO);
            } else return null;
        } else return null;
    }

    //TODO SOLO FUNCIONA CON LA RESERVA 1 1 1
    @GetMapping("/check/{idUsuario}-{idHotel}-{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable("idUsuario") Integer idUsuario,@PathVariable("idHotel") Integer idHotel,@PathVariable("idReserva") Integer idReserva){
        Boolean reservado = reservaService.checkReserva(idUsuario,idHotel,idReserva);
        return ResponseEntity.ok(reservado);
    }


    public boolean validarUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlValidar = URLUSUARIO + "/validar";
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlValidar, usuarioContrasenhaDTO,Boolean.class);
        return Boolean.TRUE.equals(responseEntity.getBody());
    }

    public Integer obtenerIdUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlObtenerInfo = URLUSUARIO + "/info/nombre/{nombre}";
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(urlObtenerInfo,Integer.class,usuarioContrasenhaDTO.getNombre());
        return responseEntity.getBody();
    }


}
