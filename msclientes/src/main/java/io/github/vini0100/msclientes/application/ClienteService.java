package io.github.vini0100.msclientes.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.vini0100.msclientes.domain.Cliente;
import io.github.vini0100.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service // Do Clientes Controller, passa aqui para fazer algumas validações antes de
         // retornar algo ou fazer alguma aplicação no repositório
@RequiredArgsConstructor // Cria um construtor de "repository" e @AutoWired para repository
public class ClienteService {

    private final ClienteRepository repository; // Uma instância do ClienteRepository que irá ajudar na operações após
                                                // as validações feitas pelo service. Deve-se colcoar Final para boas
                                                // práticas.

    @Transactional // Se uma exceção for lançada durante a execução do método, o Spring faz o
                   // rollback da transação
    public Cliente save(Cliente cliente) {
        return repository.save(cliente); // Chama o método 'save' da instância 'repository' passando como argumento o
                                         // cliente que será salvo. Save é um método extendido da JPA.
    }

    public Optional<Cliente> getByCPF(String cpf) { // Optional<Cliente> Pode ou não retornar um Cliente
        return repository.findByCpf(cpf); // Procura por CPF, o nome do método já saberá oque fazer.
    }

    public List<Cliente> listAll() {
        return repository.findAll();
    }
}
