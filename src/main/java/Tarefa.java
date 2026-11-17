import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Tarefa {
    private String titulo;
    private String descricao;
    private Estado estado;
    private Prioridade prioridade;

    public enum Estado {
        PENDENTE,
        EM_CURSO,
        CONCLUIDA;
    }

    public enum Prioridade{
        BAIXA,
        MEDIA,
        ALTA;
    }

    @JsonCreator
    //construtor
    public Tarefa(@JsonProperty("titulo") String titulo, @JsonProperty("descricao") String descricao){
        setTitulo(titulo);
        setDescricao(descricao);
        this.estado = Estado.PENDENTE;
        this.prioridade = Prioridade.BAIXA;
    }    

    //getters
    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public Estado getEstado(){
        return estado;
    }

    public Prioridade getPrioridade(){
        return prioridade;
    }

    private void setTitulo(String titulo){  
            if(titulo == null || titulo.isBlank()){
                throw new IllegalArgumentException("O título da tarefa não foi preenchido.");
            }
            this.titulo = titulo;
            
    }

    public void setPrioridade(Prioridade prioridade){
        this.prioridade = prioridade; 
    }

    private void setDescricao(String descricao){
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("A descrição não foi preenchida");
        }
        this.descricao = descricao;
    }
    
    public void comecarTarefa(){
        this.estado = Estado.EM_CURSO;
    }

    public void concluirTarefa(){
        this.estado = Estado.CONCLUIDA;
    }
    public void prioridadeMedia(){
        this.prioridade = Prioridade.MEDIA;
    }

    public void prioridadeAlta(){
        this.prioridade = Prioridade.ALTA;
    }

    //metodos

    public String toString(){
        return "Titulo: " + this.titulo +
         "\nDescrição: " + this.descricao + 
         "\nEstado: " + this.estado;

    }
}
