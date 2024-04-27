package io.github.vini0100.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoCliente {
    // Irá pegar estes dados atraves do getCartoesByCliente do mscartao

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}
