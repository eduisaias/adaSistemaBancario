package Contas;

import cliente.Classificacao;
import cliente.Cliente;
import operacoes.Banco;
import operacoes.Transacao;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente, Classificacao classificacao, String id2) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Extrato da conta poupança:");
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
    public void depositar(double valor) {
        this.saldo += valor;
        registrarTransacao("Depósito", valor);
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

        sacar(valor); //
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

	
}
