import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//classe para salvar e carrefar as tarefas, para isso nao ficar como responsabilidade do main

public class RepositorioTarefas {
    private File ficheiro;
    private ObjectMapper mapper;

    public RepositorioTarefas(File ficheiro) {
        // guarda o ficheiro recebido e cria o mapper
        this.ficheiro = ficheiro;
        this.mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public ArrayList<Tarefa> carregar() throws IOException {

        // se o ficheiro não existir ou estiver vazio -> devolve lista vazia
        if(!ficheiro.exists() || ficheiro.length() == 0){
            return new ArrayList<>();

        }else{
            return mapper.readValue(ficheiro, new TypeReference<ArrayList<Tarefa>>() {});
        }
    }

    public void guardar(ArrayList<Tarefa> tarefas) throws IOException {
        mapper.writeValue(ficheiro, tarefas);
    }
}