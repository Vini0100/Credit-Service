package io.github.vini0100.mscartoes.application;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.vini0100.mscartoes.domain.ClienteCartao;
import io.github.vini0100.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
