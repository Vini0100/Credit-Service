package io.github.vini0100.msavaliadorcredito.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.github.vini0100.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import io.github.vini0100.msavaliadorcredito.domain.model.*;
import io.github.vini0100.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import io.github.vini0100.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.vini0100.msavaliadorcredito.application.ex.ErroComunicacaoMicroServicesException;
import io.github.vini0100.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.vini0100.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroServicesException {
        // obterDadosCliente - msclientes
        // obter cartoes do cliente - mscartoes
        // Usar Try

        try { // Caso nao tenha o cliente, pode retornar erro
              // Spring identifica o id e o nome solicitado pelo DadosCliente e atribui a ele:
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            // -Identifica o nome, bandeira e limiteLiberado solicitado e atribui a ele
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())// Vai receber o body vindo de clienteResourceClient
                    .cartaoCliente(cartoesResponse.getBody())// Vai receber o body vindo de cartoesResourceClient
                    .build();
        } catch (FeignException.FeignClientException e) { // Tratamento de erros
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException(); // Mensagem de CPF não encontrado
            }
            throw new ErroComunicacaoMicroServicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroServicesException {
        try {
            // Recebo os dados do cliente
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            // Recebe uma lista dos cartoes salvos com a renda passada passada pelo cliente
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesPorRenda(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();

            // Mapear a lista de cartão disponível para uma lista de Cartao aprovado
            List<CartaoAprovado> listaCartaoAprovados = cartoes.stream().map(cartao -> {

                // Pegar o Body do cliente
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                // Antes de fazer o calculo, coloco todos os dados em Bigdecimal
                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

                // Calculo
                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList()); // Coloco as avaliações na lista

            // devolvo todos os cartoes aprovados para o cliente
            // preciso do construtor em RetornoAvaliacaoCliente
            return new RetornoAvaliacaoCliente(listaCartaoAprovados);

        } catch (FeignException.FeignClientException e) { // Tratamento de erros
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException(); // Mensagem de CPF não encontrado
            }
            throw new ErroComunicacaoMicroServicesException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try{
            emissaoCartaoPublisher.solicitarCartao(dados);
            String protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
