package com.example.reservas.controllers;


import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.DTO.UsuarioContrasenhaDTO;
import com.example.reservas.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/reservas/hotel")
@RequiredArgsConstructor
public class HotelesController {

    private final HotelService hotelService;

    private final String URLUSUARIO = "http://localhost:8702/usuarios";


    @PostMapping
    public ResponseEntity<String> crearHotel(@RequestBody HotelDTO hotelDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(hotelDTO.getNombreUsu(),hotelDTO.getContrasena()))){
            try {
                hotelService.guardar(hotelDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body("Hotel creado con éxito");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el hotel");
            }
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

    }

    @PatchMapping()
    public ResponseEntity<String> actualizarHotel(@RequestBody HotelDTO hotelDTO){
        if (validarUsuario(new UsuarioContrasenhaDTO(hotelDTO.getNombreUsu(),hotelDTO.getContrasena()))){
            boolean actualizado = hotelService.update(hotelDTO);
            if (actualizado){
                return ResponseEntity.ok().body("El hotel se ha actualizado con éxito");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido actualizar el hotel");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable Integer id,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            boolean eliminado = hotelService.delete(id);
            if (eliminado){
                return ResponseEntity.ok("Se ha eliminado el Hotel");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el hotel para eliminarlo");
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");

    }


    @PostMapping("/id/{nombre}")
    public ResponseEntity<Integer> obtenerIdApartirNombre(@PathVariable String nombre,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            Integer idHotel = hotelService.obtenerIdApartirNombre(nombre);
            return ResponseEntity.ok(idHotel);
        }else return null;

    }

    @PostMapping("/nombre/{idHotel}")
    public ResponseEntity<String> obtenerNombreAPartirId(@PathVariable Integer idHotel,@RequestBody UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        if (validarUsuario(usuarioContrasenhaDTO)){
            String nombre = hotelService.obtenerNombreAPartirId(idHotel);
            return ResponseEntity.ok(nombre);
        }else return ResponseEntity.ok().body("Usuario o contraseña incorrectos");
    }

    public boolean validarUsuario(UsuarioContrasenhaDTO usuarioContrasenhaDTO){
        RestTemplate restTemplate = new RestTemplate();
        String urlValidar = URLUSUARIO + "/validar";
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(urlValidar, usuarioContrasenhaDTO,Boolean.class);
        return Boolean.TRUE.equals(responseEntity.getBody());
    }


}
