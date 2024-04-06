package Contas;
import java.util.List;

import cliente.Cliente;
import operacoes.Banco;

public interface IConta {

	void sacar(double valor);
	void depositar(double valor);
	void transferir(double valor, String idDestino, Banco banco);


	void imprimirExtrato();
	void sacar(double valor, Cliente cliente);
	Object getId();

	default void transferir(double valor, String idDestino, List<Conta> contasDisponiveis) {
	   
	}
	void transferir(double valor, String idDestino);
}
