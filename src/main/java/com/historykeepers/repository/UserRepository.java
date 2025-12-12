package com.historykeepers.repository;

import com.historykeepers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Esta es una interfaz que hace la función de acceso a datos
// habla con la base de datos directamente

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Todos los metodos mostrados en service que se solicitan de aqui aunque no están escritos
    //Es porque los hereda de JpaRepository

    //Metodo para buscar a un usuario con su username.
    //Se implementa la consulta a partir del nombre del metodo
    Optional<User> findByUsername(String username);

}

