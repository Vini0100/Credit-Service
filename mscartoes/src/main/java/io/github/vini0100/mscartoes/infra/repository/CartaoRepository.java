package io.github.vini0100.mscartoes.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.vini0100.mscartoes.domain.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);// interface genérica fornecida pelo Spring
    // Data JPA. Ela é usada para fornecer métodos
    // CRUD (Create, Read, Update, Delete)

}
