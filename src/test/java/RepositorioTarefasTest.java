
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioTarefasTest {

    @TempDir
    Path pastaTemporaria;

    @Test
    void carregarFicheiroInexistenteDevolveListaVazia() throws IOException {

        File ficheiro = pastaTemporaria.resolve("tarefas.json").toFile();

        RepositorioTarefas repositorio = new RepositorioTarefas(ficheiro);

        ArrayList<Tarefa> tarefas = repositorio.carregar();

        assertTrue(tarefas.isEmpty());
    }

    @Test
    void guardarEDepoisCarregarDevolveAsTarefasCorretas() throws IOException {

        File ficheiro = pastaTemporaria.resolve("tarefas.json").toFile();

        RepositorioTarefas repositorio = new RepositorioTarefas(ficheiro);

        ArrayList<Tarefa> tarefasOriginais = new ArrayList<>();

        Tarefa tarefa1 = new Tarefa("Estudar Java", "Estudar JUnit");
        Tarefa tarefa2 = new Tarefa("Fazer projeto", "Programar em Java");

        tarefa1.concluirTarefa();

        tarefasOriginais.add(tarefa1);
        tarefasOriginais.add(tarefa2);

        repositorio.guardar(tarefasOriginais);

        ArrayList<Tarefa> tarefasCarregadas = repositorio.carregar();

        assertEquals(2, tarefasCarregadas.size());

        assertEquals("Estudar Java", tarefasCarregadas.get(0).getTitulo());
        assertEquals(Tarefa.Estado.CONCLUIDA, tarefasCarregadas.get(0).getEstado());

        assertEquals("Fazer projeto", tarefasCarregadas.get(1).getTitulo());
        assertEquals(Tarefa.Estado.PENDENTE, tarefasCarregadas.get(1).getEstado());
    }

    @Test
    void carregarFicheiroEstragadoLancaIOException() throws IOException {

        File ficheiro = pastaTemporaria.resolve("estragado.json").toFile();

        Files.writeString(ficheiro.toPath(), "isto nao e um JSON valido");

        RepositorioTarefas repositorio = new RepositorioTarefas(ficheiro);

        assertThrows(
            IOException.class,
            () -> repositorio.carregar()
        );
    }

    @Test
    void carregarFicheiroEstragadoNaoAlteraOSeuConteudo() throws IOException {

        File ficheiro = pastaTemporaria.resolve("estragado.json").toFile();

        String conteudoOriginal = "isto nao e um JSON valido";

        Files.writeString(ficheiro.toPath(), conteudoOriginal);

        RepositorioTarefas repositorio = new RepositorioTarefas(ficheiro);

        assertThrows(
            IOException.class,
            () -> repositorio.carregar()
        );

        String conteudoDepois = Files.readString(ficheiro.toPath());

        assertEquals(conteudoOriginal, conteudoDepois);
    }
}
