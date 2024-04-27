package io.github.vini0100.msavaliadorcredito.domain.model;

import lombok.Data;

@Data
public class DadosCliente { // Dados do cliente que vou receber da Api msclientes
    // Isto será uma representação do retorno de 'dadosCliente' de msclientes:
    private Long id;
    private String nome;
    private Integer idade;

}
