package com.historykeepers.service;

import com.historykeepers.model.User;
import com.historykeepers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Esta clase sirve como la lógica del negocio y un intermediario entre Controller y Repositoy.

@Service
public class UsuarioService {

    //Le pedimos a spring que nos de una instancia de UserRepository
    @Autowired
    private UserRepository usuarioRepositorio;

    //Metodo para obtener todos los usuarios de mi base llamando a repository
    public List<User> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    //Metodo para obtener un solo usuario pasandole su id al metodo
    public Optional<User> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    //Metodo para obtener guardar o actualizar el usuario nuevo pasandole un objeto usuario completo
    public User guardarUsuario(User usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //Metodo para eliminar al usuario, aqui tambien le mandamos el id nada más
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
