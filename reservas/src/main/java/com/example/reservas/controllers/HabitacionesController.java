package com.example.reservas.controllers;

import com.example.reservas.DTO.HabitacionDTO.ActualizarHabitacionDTO;
import com.example.reservas.DTO.HabitacionDTO.CrearHabitacionDTO;
import com.example.reservas.DTO.UsuarioContrasenhaDTO;
import com.example.reservas.entities.Hotel;
import com.example.reservas.services.HabitacionService;
import com.example.reservas.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/reservas/habitacion")
@RequiredArgsConstructor
public class HabitacionesController {

    private final HabitacionService habitacionService;
    private final HotelService hotelService;

    private final String URLUSUARIO = "http://localhost:8702/usuarios";


    @PostMapping
    public ResponseEntity<String> crearHabitacion(@RequestBody CrearHabitacionDTO crearHabitacionDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(crearHabitacionDTO.getNombre(),crearHabitacionDTO.getContrasena()))){
            Hotel hotel = hotelService.findById(crearHabitacionDTO.getHotel_id());
            if(crearHabitacionDTO.getTipo().equalsIgnoreCase("individual") || crearHabitacionDTO.getTipo().equalsIgnoreCase("doble") || crearHabitacionDTO.getTipo().equalsIgnoreCase("suite")){
                if (hotel != null){
                    boolean creada = habitacionService.crearHabitacion(crearHabitacionDTO);
                    if (creada){
                        return ResponseEntity.ok().body("La habitacion ha sido creada con éxito");
                    }else return ResponseEntity.ok().body("ERROR al crear la habitacion");
                }else ResponseEntity.ok().body("No se encuentra el hotel con esa id");
            }else return ResponseEntity.ok().body("El tipo de la habitacion es incorrecto");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

        return ResponseEntity.ok().body("No se ha podido crear la habitacion");
    }

    @PatchMapping()
    public ResponseEntity<String> actualizarHabitacion(@RequestBody ActualizarHabitacionDTO actualizarHabitacionDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(actualizarHabitacionDTO.getNombre(),actualizarHabitacionDTO.getContrasena()))){
            boolean actualizada = habitacionService.update(actualizarHabitacionDTO);
            if (actualizada){
                return ResponseEntity.ok().body("La habitacion ha sido actualizada con éxito");
            }else return ResponseEntity.ok().body("ERROR al actualizar la habitacion");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            boolean eliminado = habitacionService.eliminarHabitacion(id);
            if (eliminado){
                return ResponseEntity.ok("Se ha eliminado la habitacion");
            }else return ResponseEntity.ok().body("No se encontró la habitacion");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

    }

    public boolean validarUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlValidar = URLUSUARIO + "/validar";
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlValidar, usuarioContrasenhaDTO,Boolean.class);
        return Boolean.TRUE.equals(responseEntity.getBody());
    }


}
