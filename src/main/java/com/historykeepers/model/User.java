package com.historykeepers.model;

import jakarta.persistence.*;

@Entity//Indica que esta clase es una entidad que se mapea a una tabla en la base de datos
@Table(name = "users")//Definimos el nombre de la tabla en la base de datos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(unique = true, nullable = false)//Definimos que esta columna es unica y no puede ser nula
    private String username;

    @Column(nullable = false)//Definimos que esta columna no puede ser nula
    private String password;

    @Column(name = "correo", nullable = false, unique = true)//Definimos el nombre de la columna en la tabla
    private String correo;

    @Column(name = "nombre_completo", nullable = false)//Definimos el nombre de la columna en la tabla
    private String nombreCompleto;

    public User() {
    }

    //Constructores de la clase User
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String correo, String nombreCompleto) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
    }
    //Getters y Setters de nuestra clase User
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
