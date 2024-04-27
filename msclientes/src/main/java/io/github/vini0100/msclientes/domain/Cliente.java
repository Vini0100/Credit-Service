package io.github.vini0100.msclientes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Data vai gerar Getter, Setter, RequiredArgsConstructor, ToString,
      // EqualsAndHashCode value.
@NoArgsConstructor // Gera um construtor sem argumentos
public class Cliente {

    @Id // Informar ao Spring que isto é o ID da entidade Cliente
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento
    private Long id;

    @Column // Não é necessária esta anotação((name = "nome_produto", nullable = false,
            // length = 50))
    private String cpf;

    @Column
    private String nome;

    @Column
    private Integer idade;

    public Cliente(String cpf, String nome, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
}
