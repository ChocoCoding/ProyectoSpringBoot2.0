package com.example.usuarios.services;


import com.example.usuarios.entities.Usuario;
import com.example.usuarios.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public boolean checkIfExist(Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent();
    }

    public String obtenerInfoUsuarioPorNombre(String nombre){
        Optional<Usuario> usuario =  usuarioRepository.findByNombre(nombre);
        if (usuario.isPresent()){
            return String.valueOf(usuario.get().getUsuario_id());
        }else return null;

    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario findUsuarioById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario update(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public String obtenerInfoUsuarioPorId(Integer id){
        Optional<Usuario> usuarioInfo = usuarioRepository.findById(id);
        if (usuarioInfo.isPresent()){
            Usuario usuario = usuarioInfo.get();
            return usuario.getNombre();
        }else return null;
    }

    public boolean eliminarUsuario(String nombre,String contrasena){
        Optional<Usuario> usuarioAEliminar = usuarioRepository.findByNombreAndContrasena(nombre,contrasena);
        if (usuarioAEliminar.isPresent()){
            usuarioRepository.delete(usuarioAEliminar.get());
            return true;
        }else return false;
    }

    public boolean validarUsuario(String nombre,String contrasena){
        Optional<Usuario> usuarioValidar = usuarioRepository.findByNombreAndContrasena(nombre,contrasena);
        return usuarioValidar.isPresent();
    }

}
