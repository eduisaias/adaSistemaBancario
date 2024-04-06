package Contas;
import cliente.Classificacao;
import cliente.Cliente;
import operacoes.Transacao;


public class ContaInvestimento extends Conta {
	
	private boolean criada;
	public ContaInvestimento(Cliente cliente, Classificacao classificacao, String id3){
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
    	System.out.println("Extrato da conta investimento:");
        for (Transacao transacao : historico) {
            System.out.println(transacao.getDataHora() + " - " + transacao.getDescricao() + ": " + transacao.getValor());
        }
        
        System.out.println("Agência: " + getAgencia() + " Conta: " + getNumero() + " Saldo atual: " + getSaldo()); 
    }
    
    public boolean verificarSaldoPositivo() {
        return this.getSaldo() > 0;
    }
    
    public boolean verificarContaCriada() {
        return criada;
    }
    
    public void investir(double valor) {
        if (verificarContaCriada() && verificarSaldoPositivo()) {
            System.out.println("Investimento de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Não é possível realizar o investimento. Verifique se a conta foi criada e se possui saldo positivo.");
        }

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
	public Object getId() {
		
		return null;
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

	@Override
	public void sacar(double valor, Cliente cliente) {
	
		
	}

	
	
}
