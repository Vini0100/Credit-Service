package io.github.vini0100.msavaliadorcredito.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Facilitar a criação de um objeto SituacaoClient
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> cartaoCliente;
}
