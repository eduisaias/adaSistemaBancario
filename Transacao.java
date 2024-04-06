import java.time.LocalDate;
import java.time.LocalDateTime;

class Transacao {
    private LocalDate data;
    private String descricao;
    private double valor;

    public Transacao(LocalDate data, String descricao, double valor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return getDataHora();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}