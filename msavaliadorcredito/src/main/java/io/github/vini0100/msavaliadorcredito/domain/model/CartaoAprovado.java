package io.github.vini0100.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoAprovado { // Aqui é um único, para retornar mais terá que passar em uma List
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
