package io.github.vini0100.msclientes.application;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.vini0100.msclientes.application.representation.ClienteSaveRequest;
import io.github.vini0100.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // A classe é um controle de rota. Controlador REST, ou seja, ela trata
                // solicitações HTTP e retorna respostas HTTP, geralmente no formato JSON
@RequestMapping("/clientes") // Define o mapeamento da URL "/clientes" para as operações de controle de
                             // Endpoints nesta classe
@RequiredArgsConstructor // Vai criar um construtor para 'service' e o @AutoWired
@Slf4j // Vamos ter acesso aos logs
public class ClientesController {

    // @AutoWired não é necessário pois com a annotation @RequiredArgsConstructor o
    // Spring detecta automaticamente
    private final ClienteService service;

    @GetMapping("") // Especifica o caminho para este endpoint
    public ResponseEntity<List<Cliente>> listClientes() {
        List<Cliente> clients = service.listAll();
        if (clients.isEmpty()) {// Caso não seja encontrado nenhum cliente
            return ResponseEntity.notFound().build(); // Retorna uma resposta com status 404 Not Found
        }
        return ResponseEntity.ok(clients); // Retorna uma resposta com status 200 OK e a lista de clientes
    }


    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) { // Criar um objeto de representação, que irá
                                                                          // representar CPF, Nome e idade (DTO) do
                                                                          // cliente que estou querendo salvar.
        Cliente cliente = request.toModel();
        service.save(cliente); // O service irá se encarregar de salvar no banco de dados
        URI headerLocation = ServletUriComponentsBuilder // Serve para construir URLs dinâmicas
                .fromCurrentRequest() // Atraves da URL corrente. Ex.8080/clientes
                .query("cpf={cpf}") // Passar parâmtro via URL
                .buildAndExpand(cliente.getCpf()) // Vai buildar a URL passando o CPF
                .toUri(); // Transforma no Objeto URI
        return ResponseEntity.created(headerLocation).build(); // Retorno uma headerLocation com a URL do cliente
    }

    @GetMapping(params = "cpf") // Parametro da Query
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) { // É um parâmetro da Query de referencia e não
                                                                          // um Body
        Optional<Cliente> cliente = service.getByCPF(cpf);
        if (cliente.isEmpty()) {// Caso não for encontrado
            return ResponseEntity.notFound().build(); // Build um notFound
        }
        return ResponseEntity.ok(cliente);
    }

}
