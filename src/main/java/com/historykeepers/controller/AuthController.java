package com.historykeepers.controller;

import com.historykeepers.model.User;
import com.historykeepers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Endpoint para Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // 1. Buscar usuario en la BD
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 2. Verificar contraseña
            if (user.getPassword().equals(password)) {
                // Login exitoso: Devolvemos un objeto simple indicando éxito
                return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "userId", user.getId(),
                    "username", user.getUsername()
                ));
            }
        }

        // Login fallido
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
    }
}
