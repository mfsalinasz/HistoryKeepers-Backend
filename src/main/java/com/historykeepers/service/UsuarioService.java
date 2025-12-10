package com.historykeepers.service;

import com.historykeepers.model.User;
import com.historykeepers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository usuarioRepositorio;

    public List<User> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Optional<User> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public User guardarUsuario(User usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
