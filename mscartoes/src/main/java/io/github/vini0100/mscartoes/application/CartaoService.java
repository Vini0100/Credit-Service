package io.github.vini0100.mscartoes.application;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.vini0100.mscartoes.domain.Cartao;
import io.github.vini0100.mscartoes.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository; // @RequiredArgsConstructor injetou automaticamente o @AutoWired

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) { // Não tenho como mandar por Web um valor Bigdecimal.
                                                                // Então vai como Long e depois vira um BigDecimal
        BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal); // Retorta um List de Cartao
    }

}
