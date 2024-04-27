package io.github.vini0100.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.vini0100.msavaliadorcredito.domain.model.DadosCliente;

@FeignClient(value = "msclientes", path = "/clientes") // Mapeando msclientes com Feign
// Como eu configurar com o link do gateway, não vai para o LoadValancer
public interface ClienteResourceClient { // Vai ser cliente da minha API de cliente

    @GetMapping(params = "cpf") // Mesma assinatura do msclientes
    ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);
}
