package cliente;


public class Cliente {
    private String nome;
    private String id;
    private Classificacao classificacao;
    private Status status;
    
    

    
    public Cliente(String nome,  Classificacao classificacao, String id) {
        this.classificacao = classificacao;
        this.nome = nome;
        this.id = id;
        
    }
    
   

        

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }





	public boolean containsKey(String idDestino) {
		
		return false;
	}
}
