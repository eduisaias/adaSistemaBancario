package Contas;

import operacoes.Banco;
import operacoes.Transacao;
import cliente.Cliente;
import cliente.Status;
import cliente.Classificacao;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private final int agencia;
    private final int numero;
    protected double saldo;
    protected Cliente cliente;
    protected ArrayList<Transacao> historico;
    private Status status;
    private Banco banco;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.historico = new ArrayList<>();
        this.status = Status.ATIVO;
    }
    
    @Override
    public void sacar(double valor) {
        if (cliente.getClassificacao() == Classificacao.PJ) {
            double taxa = valor * 0.005;
            valor += taxa;
        }
        if (saldo < valor) {
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
        
        if (this.status == Status.INATIVO) {
            System.out.println("Conta inativa. Não é possível realizar transferências.");
            return;
        }
        if (idDestino == null || idDestino.trim().isEmpty()) {
            System.out.println("ID de destino não pode estar em branco");
            return;
        }

      
        if (banco == null) {
            System.out.println("Banco não fornecido");
            return;
        }

        Conta contaDestino = banco.getContaPorId(idDestino);
        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada");
            return;
        }

        double valorTransferido = valor;
        if (cliente.getClassificacao() == Classificacao.PJ) {
            double taxa = valor * 0.005;
            if (saldo < valor + taxa) {
                System.out.println("Saldo insuficiente para transferência");
                return;
            }
            saldo -= valor + taxa;
            valorTransferido = valor;
        } else {
            if (saldo < valor) {
                System.out.println("Saldo insuficiente para transferência");
                return;
            }
            saldo -= valor;
        }

        contaDestino.depositar(valorTransferido);
        registrarTransacao("Transferência para " + idDestino, -valorTransferido);
    }


    protected void registrarTransacao(String descricao, double valor) {
        LocalDateTime data = LocalDateTime.now();
        Transacao transacao = new Transacao(data, descricao, valor);
        historico.add(transacao);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void imprimirDados() {
        System.out.println(String.format("Agencia: %04d", this.agencia)); 
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println(String.format("Nome do cliente: %s", this.cliente.getNome()));
    }
    
    public void investir() {
     
    }

    
    public abstract Object getId();
    
    
    
    
    
}
