package com.historykeepers.controller;

import com.historykeepers.model.User;
import com.historykeepers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    // Obtener todos los usuarios
    @GetMapping
    public List<User> obtenerTodosUsuarios() {
        return usuarioServicio.obtenerTodosUsuarios();
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<User> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            User usuario = usuarioOpt.get();
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }

    // Crear usuario nuevo
    @PostMapping
    public User crearUsuario(@RequestBody User usuario) {
        return usuarioServicio.guardarUsuario(usuario);
    }

    // Actualizar usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Long id, @RequestBody User datosUsuario) {
        Optional<User> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            User usuarioExistente = usuarioOpt.get();

            usuarioExistente.setUsername(datosUsuario.getUsername());
            usuarioExistente.setPassword(datosUsuario.getPassword());
            usuarioExistente.setCorreo(datosUsuario.getCorreo());
            usuarioExistente.setNombreCompleto(datosUsuario.getNombreCompleto());

            User usuarioActualizado = usuarioServicio.guardarUsuario(usuarioExistente);
            return ResponseEntity.ok(usuarioActualizado);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<User> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}