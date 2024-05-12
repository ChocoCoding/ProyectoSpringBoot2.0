package com.example.usuarios.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuario_id;

    @NonNull
    @Column(name = "nombre", length = 100)
    private String nombre;

    @NonNull
    @Column(name = "correo_electronico")
    private String correo_electronico;

    @NonNull
    @Column(name = "direccion")
    private String direccion;

    @NonNull
    @Column(name = "contrasena")
    private String contrasena;



}
