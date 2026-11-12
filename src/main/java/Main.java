import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        ObjectMapper mapper = new ObjectMapper();
        File ficheiro = new File("tarefas.json");

        if (ficheiro.exists() && ficheiro.length() > 0) {
            tarefas = mapper.readValue(
                ficheiro,
                new TypeReference<ArrayList<Tarefa>>() {}
            );
        }
        
        

        while (true) {
            System.out.println("Escolha uma opção: \n1 - Criar tarefa \n2 - Listar tarefas \n3 - Mudar estado da tarefa \n4 - Excluir Tarefa \n5 - Sair");
            String entradaMenu = scanner.nextLine();
            int menu;
            try {
                menu = Integer.parseInt(entradaMenu);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (menu) {
                case 1:
                    adicionarTarefa(mapper,ficheiro, scanner, tarefas);
                    break;
                case 2:
                    listarTarefas(tarefas);
                    break;
                case 3:
                    mudarEstadoTar(mapper,ficheiro, scanner, tarefas);
                    
                    break;
                case 4:
                    excluirTarefa(mapper, ficheiro, scanner, tarefas);
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }


            
        }
        
    }
    //Métodos
    public static void adicionarTarefa(ObjectMapper mapper, File ficheiro, Scanner scanner, ArrayList<Tarefa> tarefas) throws IOException{
        //ObjectMapper mapper = new ObjectMapper();
        while (true) {
            System.out.println("Digite o título");
            String titulo = scanner.nextLine();
            //depois tem de verificar pra saber se ja tem uma tarefa com o come igual
            System.out.println("Digite a descrição");
            String descricao = scanner.nextLine();
            try {
                Tarefa t = new Tarefa(titulo, descricao);
                tarefas.add(t);
                mapper.writeValue(ficheiro, tarefas);
                System.out.println("Tarefa registrada");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }         
    }

    public static void listarTarefas(ArrayList<Tarefa> tarefas) throws IOException{

        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
        } else {
            System.out.println("-----------------------");
            for (Tarefa t : tarefas) {
                System.out.println(t);
                System.out.println("-----------------------");
            }
        }
    }

    public static void mudarEstadoTar(ObjectMapper mapper, File ficheiro, Scanner scanner, ArrayList<Tarefa> tarefas) throws IOException{
        //ObjectMapper mapper = new ObjectMapper();

        for(Tarefa t : tarefas){
            System.out.println(t.getTitulo());
            System.out.println("----------");
        }
        System.out.println("Digite o título da tarefa que quer acessar");
        String nomeT = scanner.nextLine();
        Tarefa tarefaEncontrada = null;
        for (Tarefa t : tarefas) {
            if (t.getTitulo().equalsIgnoreCase(nomeT)) {
                tarefaEncontrada = t;
                break;
            }
        }

        if (tarefaEncontrada == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("O estado atual da tarefa " + tarefaEncontrada.getTitulo() + " é: " + tarefaEncontrada.getEstado());
        System.out.println("Opções: \n1 - Em curso \n2 - Concluída");
        String entradaOpcao = scanner.nextLine();
        try {
            int opcao = Integer.parseInt(entradaOpcao);
            switch (opcao) {
                case 1:
                    tarefaEncontrada.comecarTarefa();
                    break;
                case 2:
                    tarefaEncontrada.concluirTarefa();
                    break;
                default:
                    System.out.println("Opção inválida.");
            
            }
            mapper.writeValue(ficheiro, tarefas);
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
        }
    }

    public static void excluirTarefa(ObjectMapper mapper, File ficheiro, Scanner scanner, ArrayList<Tarefa> tarefas) throws IOException{
        System.out.println("Esta é a lista de tarefas no momento:");
        for(Tarefa t : tarefas){
            System.out.println(t.getTitulo());
        }
        while (true) {
            System.out.println("Nome da que quer excluir: ");
            String tarExc = scanner.nextLine();
            boolean tEncontrada = false;
            
            for(Tarefa t : tarefas){
                if(t.getTitulo().equals(tarExc)){
                    tarefas.remove(t);
                    tEncontrada = true;
                    mapper.writeValue(ficheiro, tarefas);
                    break;
                }
            }
            if(!tEncontrada){
                System.out.println("Tarefa não encontrada, digite novamente: ");
                continue;
            }
            break;
        }
            
    }
}
