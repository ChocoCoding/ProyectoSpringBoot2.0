package com.example.usuarios.controllers;


import com.example.usuarios.DTO.ActualizarUsuarioDTO;
import com.example.usuarios.DTO.CrearUsuarioDTO;
import com.example.usuarios.DTO.EliminarUsuarioDTO;
import com.example.usuarios.DTO.ValidarUsuarioDTO;
import com.example.usuarios.entities.Usuario;
import com.example.usuarios.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;


    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO){
        if (usuarioService.guardar(crearUsuarioDTO)){
            return ResponseEntity.ok().body("El usuario ha sido creado con éxito");
        }else return ResponseEntity.ok().body("No se ha podido crear al usuario");

    }

    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO){
        Usuario usuario = usuarioService.findUsuarioById(actualizarUsuarioDTO.getId());
        if (usuario != null){
            usuario.setNombre(actualizarUsuarioDTO.getNombre());
            usuario.setCorreo_electronico(actualizarUsuarioDTO.getCorreo_electronico());
            usuario.setDireccion(actualizarUsuarioDTO.getDireccion());
            usuario.setContrasena(actualizarUsuarioDTO.getContrasena());

            usuarioService.update(usuario);
            return ResponseEntity.ok("El usuario se ha actualizado correctamente");
        }else return ResponseEntity.ok().body("No se ha podido encontrar al usuario");
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarUsuario(@RequestBody EliminarUsuarioDTO eliminarUsuarioDTO){
        boolean eliminado = usuarioService.eliminarUsuario(eliminarUsuarioDTO.getNombre(),eliminarUsuarioDTO.getContrasena());
        if (eliminado){
            return ResponseEntity.ok("El usuario ha sido eliminado con éxito");
        }else
            return ResponseEntity.ok().body("No se pudo eliminar al usuario");
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody ValidarUsuarioDTO validarUsuarioDTO){
        boolean esValido = usuarioService.validarUsuario(validarUsuarioDTO.getNombre(),validarUsuarioDTO.getContrasena());
        return ResponseEntity.ok(esValido);
    }


    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable Integer id){
        String nombreUsuario = usuarioService.obtenerInfoUsuarioPorId(id);
        if (nombreUsuario != null){
            return ResponseEntity.ok(nombreUsuario);
        }else return ResponseEntity.ok().body("No se ha encontrado al usuario");
    }

    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre){
        String idUsuario = usuarioService.obtenerInfoUsuarioPorNombre(nombre);
        if (idUsuario != null){
            return ResponseEntity.ok(idUsuario);
        }else return ResponseEntity.ok().body("No se ha encontrado al usuario");
    }

    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable Integer id){
        boolean existe = usuarioService.checkIfExist(id);
        return ResponseEntity.ok(existe);
        }

















}
