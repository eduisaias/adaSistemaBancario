package operacoes;

import java.time.LocalDateTime;

public class Transacao {
    private LocalDateTime dataHora;
    private String descricao;
    private double valor;

    public Transacao(LocalDateTime dataHora, String descricao, double valor) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}
