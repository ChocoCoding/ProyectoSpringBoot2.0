package com.example.reservas.controllers;

import com.example.reservas.DTO.DelvolverReservaDTO;
import com.example.reservas.DTO.ReservaDTO;
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
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        if (validarUsuario(new UsuarioContrasenhaDTO(reservaDTO.getNombre(),reservaDTO.getContrasena()))){
            boolean creada = reservaService.guardar(reservaDTO);
            if (creada){
                return ResponseEntity.ok().body("La reserva se ha creado con éxito");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido realizar la reserva");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    @PatchMapping
    public ResponseEntity<String> cambiarEstado(@RequestBody ReservaDTO reservaDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(reservaDTO.getNombre(),reservaDTO.getContrasena()))){
            boolean cambiado = reservaService.updateEstadoById(reservaDTO);
            if (cambiado){
                return ResponseEntity.ok().body("El estado se ha modificado con éxito");
            }else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se ha podido realizar la reserva, comprueba el estado introducido");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    @GetMapping()
    public ResponseEntity<List<DelvolverReservaDTO>> listarReservasUsuario(@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO) {
        Integer idUsuario = Integer.parseInt(obtenerIdUsuario(usuarioContrasenhaDTO));
        if (validarUsuario(usuarioContrasenhaDTO)) {
            List<DelvolverReservaDTO> delvolverReservaDTO = reservaService.listarReservasUsuario(idUsuario);
            if (!delvolverReservaDTO.isEmpty()) {
                return ResponseEntity.ok().body(delvolverReservaDTO);
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else return null;
    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<ReservaDTO>> listarReservasSegunEstado(@PathVariable String estado,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO) {
        if (validarUsuario(usuarioContrasenhaDTO)) {
            List<ReservaDTO> reservasDTO = reservaService.listarReservasSegunEstado(estado).stream()
                    .map(ReservaDTO::new).toList();

            if (!reservasDTO.isEmpty()) {
                return ResponseEntity.ok().body(reservasDTO);
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else return null;
    }

    @GetMapping("/check/{idUsuario}-{idHotel}-{idReserva}")
    public ResponseEntity<Boolean> checkReserva(@PathVariable("idUsuario") Integer idUsuario,@PathVariable("idHotel") Integer idHotel,@PathVariable("idReserva") Integer idReserva){
        Boolean reservado = reservaService.checkReserva(idUsuario,idHotel,idReserva);
        if (Boolean.TRUE.equals(reservado)){
            return ResponseEntity.ok(true);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
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


}
