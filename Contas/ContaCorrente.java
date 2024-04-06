package Contas;

import cliente.Classificacao;
import cliente.Cliente;
import operacoes.Banco;
import operacoes.Transacao;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente, Classificacao classificacao, String id1) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Extrato da conta corrente:");
        for (Transacao transacao : historico) {
            System.out.println(transacao.getDataHora() + " - " + transacao.getDescricao() + ": " + transacao.getValor());
        }
        System.out.println("Agência: " + getAgencia() + " Conta: " + getNumero() + " Saldo atual: " + getSaldo());
        super.imprimirDados();
    }

    @Override
    public void sacar(double valor) {
        if (getSaldo() < valor) {
            System.out.println("Saldo insuficiente para saque");
            return;
        }
        this.saldo -= valor;
        registrarTransacao("Saque", -valor);
    }

    @Override
    public void transferir(double valor, String idDestino, Banco banco) {
        if (getSaldo() < valor) {
            System.out.println("Saldo insuficiente para transferência");
            return;
        }

        Conta contaDestino = banco.getContaPorId(idDestino);
        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada");
            return;
        }

        sacar(valor); 
        contaDestino.depositar(valor); 
        registrarTransacao("Transferência para " + idDestino, -valor);
    }

    @Override
    public Object getId() {
        return null;
    }

	@Override
	public void sacar(double valor, Cliente cliente) {
		
		
	}

	@Override
	public void transferir(double valor, String idDestino) {
		
		
	}
	@Override
	public void investir() {
        double taxaRendimento = cliente.getClassificacao() == Classificacao.PF ? 0.01 : 0.02;
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        registrarTransacao("Rendimento de juros mensal", rendimento);
    }
}
