package com.example.reservas.controllers;

import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.DTO.UsuarioContrasenhaDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.services.HabitacionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/reservas/habitacion")
@RequiredArgsConstructor
public class HabitacionesController {

    private final HabitacionService habitacionService;

    private final String URLUSUARIO = "http://localhost:8702/usuarios";


    @PostMapping
    public ResponseEntity<String> crearHabitacion(@RequestBody HabitacionDTO habitacionDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(habitacionDTO.getNombre(),habitacionDTO.getContrasena()))){
            boolean creada = habitacionService.crearHabitacion(habitacionDTO);
            if (creada){
                return ResponseEntity.status(HttpStatus.CREATED).body("La habitacion ha sido actualizada con éxito");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR al actualizar la habitacion");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

    }

    @PatchMapping()
    public ResponseEntity<String> actualizarHabitacion(@RequestBody HabitacionDTO habitacionActualizada){
        if (validarUsuario(new UsuarioContrasenhaDTO(habitacionActualizada.getNombre(),habitacionActualizada.getContrasena()))){
            boolean actualizada = habitacionService.update(habitacionActualizada);
            if (actualizada){
                return ResponseEntity.ok().body("La habitacion ha sido actualizada con éxito");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR al actualizar la habitacion");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");


    }

    //TODO PREGUNTAR SI ESTA BIEN
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Integer id,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            boolean eliminado = habitacionService.eliminarHabitacion(id);
            if (eliminado){
                return ResponseEntity.ok("Se ha eliminado la habitacion");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la habitacion");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

    }

    public boolean validarUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlValidar = URLUSUARIO + "/validar";
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlValidar, usuarioContrasenhaDTO,Boolean.class);
        return Boolean.TRUE.equals(responseEntity.getBody());
    }


}
