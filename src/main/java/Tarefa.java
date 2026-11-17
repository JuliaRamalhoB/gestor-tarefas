import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Tarefa {
    private String titulo;
    private String descricao;
    private Estado estado;
    private Prioridade prioridade;
    private LocalDate dataLim;

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
        this.dataLim = null;
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

    public LocalDate getDataLimite(){
        return dataLim;
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

    public void setDataLimite(LocalDate datalim){
        this.dataLim = datalim;
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

    public String toString() {
        String data = dataLim == null ? "Sem data" : dataLim.toString();

        return  "Título: " + titulo +
                "\nDescrição: " + descricao +
                "\nEstado: " + estado +
                "\nPrioridade: " + prioridade +
                "\nData limite: " + data;
}
}
