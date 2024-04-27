package io.github.vini0100.msclientes.application.representation;

import io.github.vini0100.msclientes.domain.Cliente;
import lombok.Data;

@Data // Data vai gerar Getter, Setter, RequiredArgsConstructor, ToString,
// EqualsAndHashCode value.
public class ClienteSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel() {// Irá retornar o ClienteSaveRequest em um Cliente passando apenas o cpf,
                              // nome e idade. COnforme o contrutor da Classe Cliente.
        return new Cliente(cpf, nome, idade);
    }
}
