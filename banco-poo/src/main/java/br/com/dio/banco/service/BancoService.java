package br.com.dio.banco.service;

import br.com.dio.banco.models.*;
import br.com.dio.banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BancoService {

    private final ContaRepository repo = new ContaRepository();

    public Conta criarConta(TipoConta tipo, String agencia, String titular, String pixChave) {
        Conta conta = switch (tipo) {
            case CORRENTE -> new ContaCorrente();
            case POUPANCA -> new ContaPoupanca();
        };
        conta.setTipo(tipo);
        conta.setAgencia(agencia);
        conta.setTitular(titular);
        conta.setNumero(gerarNumero());
        conta.setPixChave(pixChave);
        conta.setSaldo(BigDecimal.ZERO);
        repo.save(conta);
        return conta;
    }

    public void depositar(String numero, BigDecimal valor) {
        Conta c = repo.findByNumero(numero).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        c.depositar(valor);
    }

    public void sacar(String numero, BigDecimal valor) {
        Conta c = repo.findByNumero(numero).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        c.sacar(valor);
    }

    public void transferirPix(String numeroOrigem, String pixDestino, BigDecimal valor) {
        Conta origem = repo.findByNumero(numeroOrigem).orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada."));
        Conta destino = repo.findByPix(pixDestino).orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado pela chave PIX."));

        origem.sacar(valor);
        origem.registrarPixEnvio(valor, "PIX para " + pixDestino);
        destino.depositar(valor);
        destino.registrarPixRecebimento(valor, "PIX de " + origem.getNumero());
    }

    public void criarInvestimento(String numero, String tipo, BigDecimal valor) {
        Conta c = repo.findByNumero(numero).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        c.sacar(valor);
        c.getInvestimentos().add(new Investimento(tipo, valor, LocalDate.now()));
        c.registrarInvestimento(valor, "Aplicação em " + tipo);
    }

    public String extratoFormatado(String numero) {
        Conta c = repo.findByNumero(numero).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        StringBuilder sb = new StringBuilder();
        sb.append("=== Extrato da Conta ").append(c.getNumero()).append(" (").append(c.getTitular()).append(") ===\n");
        c.getHistorico().forEach(t ->
                sb.append(t.dataHora()).append(" | ").append(t.tipo()).append(" | R$ ").append(t.valor())
                        .append(" | ").append(t.descricao()).append("\n")
        );
        sb.append("Saldo atual: R$ ").append(c.getSaldo());
        return sb.toString();
    }

    public List<Conta> listarContas() {
        return repo.findAll();
    }

    private String gerarNumero() {
        // Número simples e único (ex: 123456-7)
        String raw = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        return raw.substring(0, 6) + "-" + raw.substring(6, 7);
        }
}
