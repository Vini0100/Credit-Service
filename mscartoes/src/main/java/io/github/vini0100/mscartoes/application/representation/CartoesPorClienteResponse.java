package io.github.vini0100.mscartoes.application.representation;

import java.math.BigDecimal;

import io.github.vini0100.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(
                model.getCartao().getNome(), // Nome do Cartão
                model.getCartao().getBandeira().toString(), // Nome da Bandeira
                model.getLimite()); // Pegar limite
    }
}
