package io.github.vini0100.mscartoes.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Data vai gerar Getter, Setter, RequiredArgsConstructor, ToString,
      // EqualsAndHashCode value.
@NoArgsConstructor // Gera um construtor sem argumentos
public class Cartao {

    @Id // Informar ao Spring que isto é o ID da entidade Cliente
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING) // Salvar a instancia recebida de Enum como String
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

}
