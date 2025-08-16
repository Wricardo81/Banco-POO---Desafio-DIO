package br.com.dio.banco.models;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Conta {
    private String numero;
    private String agencia;
    private String titular;
    private String pixChave;
    private BigDecimal saldo = BigDecimal.ZERO;
    private TipoConta tipo;
    private final List<Transacao> historico = new ArrayList<>();
    private final List<Investimento> investimentos = new ArrayList<>();

    public void depositar(BigDecimal valor) {
        validarValorPositivo(valor);
        saldo = saldo.add(valor);
        historico.add(new Transacao(LocalDateTime.now(), TipoTransacao.DEPOSITO, valor, "Depósito"));
    }

    public void sacar(BigDecimal valor) {
        validarValorPositivo(valor);
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo = saldo.subtract(valor);
        historico.add(new Transacao(LocalDateTime.now(), TipoTransacao.SAQUE, valor, "Saque"));
    }

    public void registrarPixEnvio(BigDecimal valor, String descricao) {
        historico.add(new Transacao(LocalDateTime.now(), TipoTransacao.PIX_ENVIO, valor, descricao));
    }

    public void registrarPixRecebimento(BigDecimal valor, String descricao) {
        historico.add(new Transacao(LocalDateTime.now(), TipoTransacao.PIX_RECEBIMENTO, valor, descricao));
    }

    public void registrarInvestimento(BigDecimal valor, String desc) {
        historico.add(new Transacao(LocalDateTime.now(), TipoTransacao.INVESTIMENTO_APLICACAO, valor, desc));
    }

    private void validarValorPositivo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo.");
        }
    }

    @Override
    public String toString() {
        return "[%s] Agência %s | Conta %s | Titular %s | Saldo: %s | PIX: %s"
                .formatted(tipo, agencia, numero, titular, saldo, pixChave == null ? "-" : pixChave);
    }
}
