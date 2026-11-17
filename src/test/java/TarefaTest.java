
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void tarefaNovaComecaPendente() {
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");

        assertEquals(Tarefa.Estado.PENDENTE, tarefa.getEstado());
    }

    @Test
    void comecarTarefaMudaEstadoParaEmCurso() {
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");

        tarefa.comecarTarefa();

        assertEquals(Tarefa.Estado.EM_CURSO, tarefa.getEstado());
    }

    @Test
    void concluirTarefaMudaEstadoParaConcluida() {
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");

        tarefa.concluirTarefa();

        assertEquals(Tarefa.Estado.CONCLUIDA, tarefa.getEstado());
    }

    @Test
    void tituloVazioLancaExcecao() {
        IllegalArgumentException excecao = assertThrows(
            IllegalArgumentException.class,
            () -> new Tarefa("  ", "Estudar JUnit")
        );

        assertEquals(
            "O título da tarefa não foi preenchido.",
            excecao.getMessage()
        );
    }

    @Test
    void tituloNullLancaExcecao() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Tarefa(null, "Estudar JUnit")
        );
    }

    @Test
    void descricaoVaziaLancaExcecao() {
        IllegalArgumentException excecao = assertThrows(
            IllegalArgumentException.class,
            () -> new Tarefa("Estudar Java", " ")
        );

        assertEquals(
            "A descrição não foi preenchida",
            excecao.getMessage()
        );
    }
    @Test 
    void mudarPrioridadeParaAlta(){
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");

        tarefa.prioridadeAlta();
        assertEquals(Tarefa.Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test 
    void definirData(){
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");
        LocalDate dataLimite = LocalDate.of(2026, 11, 20);

        tarefa.setDataLimite(dataLimite);

        assertEquals(dataLimite, tarefa.getDataLimite());
    }
}
