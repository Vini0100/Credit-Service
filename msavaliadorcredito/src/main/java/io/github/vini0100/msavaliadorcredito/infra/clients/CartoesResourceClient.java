package io.github.vini0100.msavaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.vini0100.msavaliadorcredito.domain.model.Cartao;
import io.github.vini0100.msavaliadorcredito.domain.model.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes") // Mapeando mscartoes com Feign
// Como eu configurar com o link do gateway, não vai para o LoadValancer
public interface CartoesResourceClient { // Vai ser cliente da minha API de carto

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Cartao>> getCartoesPorRenda(@RequestParam("renda") Long renda);

}