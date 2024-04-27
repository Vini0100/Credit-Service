package io.github.vini0100.msavaliadorcredito.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente { // Dentro dele, terei uma lista de CartaoAprovado
    private List<CartaoAprovado> cartoes;
}
