package io.github.vini0100.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado.");
    }
}
