package io.github.vini0100.mscartoes.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.vini0100.mscartoes.application.representation.CartaoSaveRequest;
import io.github.vini0100.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.vini0100.mscartoes.domain.Cartao;
import io.github.vini0100.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
@Slf4j // Vamos ter acesso aos logs
public class CartoesController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) { // Recebe um Body contendo os dados do
                                                                             // cartão sem o Id
        Cartao cartao = request.ToModel(); // Instancia um Cartão e passa para ele o request
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesPorRenda(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list); // retorna uma instância de ResponseEntity contendo uma lista de objetos do tipo
                                        // Cartao.
    }

    @GetMapping(params = "cpf") // GetMapping de ClienteCartao com parâmetro de cpf
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf); // lista recebe list de cartoes por cpf
        List<CartoesPorClienteResponse> resultList = lista.stream() // Converte result para um fluxo (stream)
                .map(CartoesPorClienteResponse::fromModel)// Para cada elemento no fluxo, aplica a função
                                                          // CartoesPorClienteResponse::fromModel
                .collect(Collectors.toList()); // Coleta os resultados dessa operação em uma nova lista
        return ResponseEntity.ok(resultList); // Retorna a lista de Cartoes por CPF aplicando o modelo da classe
                                              // CartoesPorClienteResponse
    }
}
