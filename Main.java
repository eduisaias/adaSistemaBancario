import java.util.Scanner;
import Contas.Conta;
import Contas.ContaCorrente;
import Contas.ContaInvestimento;
import Contas.ContaPoupanca;
import cliente.Classificacao;
import cliente.Cliente;
import operacoes.Banco;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        Banco meuBanco = new Banco("BANCO ADA"); 

       
        System.out.println("Digite o nome do primeiro cliente:");
        String nome1 = sc.nextLine();

        Classificacao classificacao1 = obterClassificacao(sc);

        System.out.println("Digite o CPF ou CNPJ do primeiro cliente:");
        String id1 = sc.nextLine();

        Cliente cliente1 = new Cliente(nome1, classificacao1, id1);

       
        System.out.println("Digite o nome do segundo cliente:");
        String nome2 = sc.nextLine();

        Classificacao classificacao2 = obterClassificacao(sc);

        System.out.println("Digite o CPF ou CNPJ do segundo cliente:");
        String id2 = sc.nextLine();

        Cliente cliente2 = new Cliente(nome2, classificacao2, id2);

     
        System.out.println("Digite o nome do terceiro cliente:");
        String nome3 = sc.nextLine();

        Classificacao classificacao3 = obterClassificacao(sc);

        System.out.println("Digite o CPF ou CNPJ do terceiro cliente:");
        String id3 = sc.nextLine();

        Cliente cliente3 = new Cliente(nome3, classificacao3, id3);

     
        Conta cc1 = new ContaCorrente(cliente1, classificacao1, id1);
        Conta cp1 = new ContaPoupanca(cliente2, classificacao2, id2);
        Conta cinv1 = new ContaInvestimento(cliente3, classificacao3, id3);


        meuBanco.addConta(cc1);
        meuBanco.addConta(cp1);
        meuBanco.addConta(cinv1);

        
    
        cc1.depositar(100);
        System.out.println("Saldo antes da transferência: " + cc1.getSaldo());
        cc1.transferir(18, id2, meuBanco);
        System.out.println("Saldo após a transferência: " + cc1.getSaldo());
        
        
        
        
        cp1.imprimirExtrato();
        cc1.imprimirExtrato();
        cinv1.depositar(100);
        cinv1.investir();
        
        sc.close();
    }

    
    private static Classificacao obterClassificacao(Scanner sc) {
        while (true) {
            System.out.println("Digite a classificação do cliente (PF ou PJ):");
            String classificacaoInput = sc.nextLine().toUpperCase();
            if (classificacaoInput.equals("PF")) {
                return Classificacao.PF;
            } else if (classificacaoInput.equals("PJ")) {
                return Classificacao.PJ;
            } else {
                System.out.println("Classificação inválida. Use PF ou PJ.");
            }
        }
    }
}
