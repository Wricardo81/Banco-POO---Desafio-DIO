package br.com.dio.banco;

import br.com.dio.banco.models.TipoConta;
import br.com.dio.banco.service.BancoService;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BancoService service = new BancoService();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Banco POO - DIO ===");
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("\n1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir PIX");
            System.out.println("5 - Criar Investimento");
            System.out.println("6 - Ver Extrato");
            System.out.println("7 - Listar Contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Titular: ");
                        String titular = sc.nextLine();
                        System.out.print("Agência (ex: 0001): ");
                        String agencia = sc.nextLine();
                        System.out.print("Tipo (1=CORRENTE, 2=POUPANCA): ");
                        int t = Integer.parseInt(sc.nextLine());
                        TipoConta tipo = (t == 2) ? TipoConta.POUPANCA : TipoConta.CORRENTE;
                        System.out.print("Chave PIX (email/cpf/telefone - opcional): ");
                        String pix = sc.nextLine().trim();
                        var conta = service.criarConta(tipo, agencia, titular, pix.isEmpty() ? null : pix);
                        System.out.println("Conta criada: " + conta.getNumero() + " | PIX: " + (conta.getPixChave() == null ? "-" : conta.getPixChave()));
                    }
                    case 2 -> {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        System.out.print("Valor do depósito: ");
                        BigDecimal valor = new BigDecimal(sc.nextLine());
                        service.depositar(numero, valor);
                        System.out.println("Depósito realizado.");
                    }
                    case 3 -> {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        System.out.print("Valor do saque: ");
                        BigDecimal valor = new BigDecimal(sc.nextLine());
                        service.sacar(numero, valor);
                        System.out.println("Saque realizado.");
                    }
                    case 4 -> {
                        System.out.print("Conta origem (número): ");
                        String origem = sc.nextLine();
                        System.out.print("PIX do destinatário: ");
                        String pix = sc.nextLine();
                        System.out.print("Valor da transferência: ");
                        BigDecimal valor = new BigDecimal(sc.nextLine());
                        service.transferirPix(origem, pix, valor);
                        System.out.println("PIX enviado.");
                    }
                    case 5 -> {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        System.out.print("Tipo do investimento (ex: CDB): ");
                        String tipoInv = sc.nextLine();
                        System.out.print("Valor da aplicação: ");
                        BigDecimal valor = new BigDecimal(sc.nextLine());
                        service.criarInvestimento(numero, tipoInv, valor);
                        System.out.println("Investimento criado.");
                    }
                    case 6 -> {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        System.out.println(service.extratoFormatado(numero));
                    }
                    case 7 -> service.listarContas().forEach(System.out::println);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }
}
