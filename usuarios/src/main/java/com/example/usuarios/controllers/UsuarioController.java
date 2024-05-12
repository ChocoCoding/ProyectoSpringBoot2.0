package com.example.usuarios.controllers;


import com.example.usuarios.DTO.RequestUsuarioDTO;
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

    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> checkIfExist(@PathVariable Integer id){
        boolean existe = usuarioService.checkIfExist(id);
            return ResponseEntity.ok(existe);
        }


    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<String> obtenerInfoUsuarioPorNombre(@PathVariable String nombre){
        String idUsuario = usuarioService.obtenerInfoUsuarioPorNombre(nombre);
        if (idUsuario != null){
            return ResponseEntity.ok(idUsuario);
        }else return ResponseEntity.notFound().build();

    }

    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable Integer id){
        String nombreUsuario = usuarioService.obtenerInfoUsuarioPorId(id);
        if (nombreUsuario != null){
            return ResponseEntity.ok(nombreUsuario);
        }else return ResponseEntity.notFound().build();

    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody RequestUsuarioDTO requestUsuarioDTO){
        boolean esValido = usuarioService.validarUsuario(requestUsuarioDTO.getNombre(),requestUsuarioDTO.getContrasena());
        return ResponseEntity.ok(esValido);
    }


    @DeleteMapping
    public ResponseEntity<String> eliminarUsuario(@RequestBody RequestUsuarioDTO requestUsuarioDTO){
        boolean eliminado = usuarioService.eliminarUsuario(requestUsuarioDTO.getNombre(),requestUsuarioDTO.getContrasena());
        if (eliminado){
            return ResponseEntity.ok("El usuario ha sido eliminado con éxito");
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró al usuario");
    }


    @PostMapping("/registrar")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario){
        usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario ha sido creado con éxito");
    }


    @PutMapping("/registrar/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Integer id,@RequestBody Usuario usuarioActualizado){
        Usuario usuario = usuarioService.findUsuarioById(id);
        if (usuario != null){
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setCorreo_electronico(usuarioActualizado.getCorreo_electronico());
            usuario.setDireccion(usuarioActualizado.getDireccion());
            usuario.setContrasena(usuarioActualizado.getContrasena());

            usuarioService.guardar(usuario);
            return ResponseEntity.ok("El usuario se ha actualizado correctamente");
        }else return ResponseEntity.notFound().build();
    }

}
