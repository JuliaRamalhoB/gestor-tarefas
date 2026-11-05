import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Escolha uma opção: \n1 - Criar tarefa \n2 - Listar tarefas \n3 - Mudar estado da tarefa \n4 - Sair");
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
                    adicionarTarefa(scanner, tarefas);
                    break;
                case 2:
                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas.");
                    } else {
                        for (Tarefa t : tarefas) {
                            System.out.println(t);
                            System.out.println("-----------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Digite o título da tarefa que quer acessar");
                    for(Tarefa t : tarefas){
                        System.out.println(t.getTitulo());
                        System.out.println("----------");
                    }
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
                        break;
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
                    } catch (NumberFormatException e) {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }


            
        }
        //Métodos
        public static void adicionarTarefa(Scanner scanner, ArrayList<Tarefa> tarefas){
            while (true) {
                    System.out.println("Digite o título");
                    String titulo = scanner.nextLine();
                    System.out.println("Digite a descrição");
                    String descricao = scanner.nextLine();
                    try {
                        Tarefa t = new Tarefa(titulo, descricao);
                        tarefas.add(t);
                        System.out.println("Tarefa registrada");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
            }
                    
        }
    }
}
