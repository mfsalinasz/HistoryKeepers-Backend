package com.historykeepers.controller;

import com.historykeepers.model.User;
import com.historykeepers.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Esta clase funciona como capa de control y expone los endpoins para realizar los GET, POST, PUT o DELETE segun cual se quiera realizar

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    //le pedimos a spring que nos de una instancia de UsuarioService para que la maneje el
    @Autowired
    private UsuarioService usuarioServicio;


    // ENDPOINT GET para obtener todos los usuarios
    @GetMapping
    public List<User> obtenerTodosUsuarios() {
        return usuarioServicio.obtenerTodosUsuarios();
    }

    // Tambien ENDPOINT GET para obtener usuario pero con su ID
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<User> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            User usuario = usuarioOpt.get();
            //Armamos el ResponseEntity con un 200 de que todo salió bien y mandamos de regreso el objeto usuario
            return ResponseEntity.ok(usuario);
        }

        //Como no se encontró al usuario entonces armamos el ResponseEntity devuelve un 404 de no encontrado.
        return ResponseEntity.notFound().build();
    }

    // ENDPOINT POST para crear un usuario nuevo
    @PostMapping
    public User crearUsuario(@RequestBody User usuario) {
        return usuarioServicio.guardarUsuario(usuario);
    }

    // ENDPOINT PUT para actualizar usuario existente
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

    // ENDPOINT DELETE para eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        Optional<User> usuarioOpt = usuarioServicio.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            usuarioServicio.eliminarUsuario(id);

            //Aqui solo ocupamos el codigo de estado asi que solo mandamos eso y lo construimos
            return ResponseEntity.ok().build();
        }

        //Aqui igual solo ocupamos el codigo de estado asi que solo mandamos eso y lo construimos
        return ResponseEntity.notFound().build();
    }
}