package io.github.vini0100.mscartoes.application.representation;

import java.math.BigDecimal;
import io.github.vini0100.mscartoes.domain.BandeiraCartao;
import io.github.vini0100.mscartoes.domain.Cartao;
import lombok.Data;

@Data
public class CartaoSaveRequest { // Preencehr com os dados que quero receberS
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao ToModel() {
        return new Cartao(nome, bandeira, renda, limite);
    }
}
