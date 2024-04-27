package io.github.vini0100.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Cartao {
    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal limiteBasico;
}
