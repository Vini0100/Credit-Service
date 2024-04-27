package io.github.vini0100.msclientes.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.vini0100.msclientes.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {// interface genérica fornecida pelo Spring
    // Data JPA. Ela é usada para fornecer métodos
    // CRUD (Create, Read, Update, Delete)

    Optional<Cliente> findByCpf(String cpf);
}
