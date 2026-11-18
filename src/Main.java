
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // Vetor com capacidade para 10 reservas
    static Reserva[] reservas = new Reserva[10];

    // Contador de reservas cadastradas
    static int quantidadeReservas = 0;

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE RESERVAS =====");
            System.out.println("1 - Nova reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reserva por hóspede");
            System.out.println("4 - Ordenar reservas por dias");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    novaReserva();
                    break;

                case 2:
                    listarReservas();
                    break;

                case 3:
                    buscarReserva();
                    break;

                case 4:
                    ordenarReservas();
                    break;

                case 5:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }

    // Método auxiliar para leitura de números inteiros
    public static int lerInteiro() {

        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número inteiro válido: ");
            scanner.nextLine();
        }

        int valor = scanner.nextInt();
        scanner.nextLine();

        return valor;
    }

    // Método auxiliar para leitura de valores decimais
    public static double lerDouble() {

        while (true) {

            String entrada = scanner.nextLine().trim()
                    .replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor numérico válido: ");
            }
        }
    }

    // 1 - Cadastrar nova reserva
    public static void novaReserva() {

        if (quantidadeReservas >= reservas.length) {
            System.out.println("Limite de reservas atingido!");
            return;
        }

        System.out.println("\n===== NOVA RESERVA =====");

        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        while (nome.trim().isEmpty()) {
            System.out.print("O nome não pode ficar vazio. Digite novamente: ");
            nome = scanner.nextLine();
        }

        System.out.print("Tipo do quarto: ");
        String quarto = scanner.nextLine();

        while (quarto.trim().isEmpty()) {
            System.out.print("O tipo do quarto não pode ficar vazio. Digite novamente: ");
            quarto = scanner.nextLine();
        }

        int dias;

        do {
            System.out.print("Número de dias: ");
            dias = lerInteiro();

            if (dias < 1) {
                System.out.println("O número de dias deve ser no mínimo 1!");
            }

        } while (dias < 1);

        double diaria;

        do {
            System.out.print("Valor da diária: R$ ");
            diaria = lerDouble();

            if (diaria <= 0 || !Double.isFinite(diaria)) {
                System.out.println("O valor da diária deve ser maior que zero!");
            }

        } while (diaria <= 0 || !Double.isFinite(diaria));

        // Criação do objeto através do construtor
        reservas[quantidadeReservas] =
                new Reserva(nome, quarto, dias, diaria);

        quantidadeReservas++;

        System.out.println("Reserva cadastrada com sucesso!");
    }

    // 2 - Listar todas as reservas
    public static void listarReservas() {

        System.out.println("\n===== LISTA DE RESERVAS =====");

        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        for (int i = 0; i < quantidadeReservas; i++) {
            System.out.println(reservas[i].toString());
        }
    }

    // 3 - Buscar reserva pelo nome do hóspede
    public static void buscarReserva() {

        System.out.println("\n===== BUSCAR RESERVA =====");

        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.print("Digite o nome ou parte do nome do hóspede: ");
        String nomeBusca = scanner.nextLine().trim();

        boolean encontrado = false;

        for (int i = 0; i < quantidadeReservas; i++) {

            if (reservas[i].getNomeHospede()
                    .toLowerCase()
                    .contains(nomeBusca.toLowerCase())) {

                System.out.println(reservas[i].toString());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhuma reserva encontrada.");
        }
    }

    // 4 - Ordenar reservas por quantidade de dias
    public static void ordenarReservas() {

        System.out.println("\n===== ORDENAR RESERVAS =====");

        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        // Bubble Sort em ordem decrescente
        for (int i = 0; i < quantidadeReservas - 1; i++) {

            for (int j = 0; j < quantidadeReservas - 1 - i; j++) {

                if (reservas[j].getNumeroDias()
                        < reservas[j + 1].getNumeroDias()) {

                    Reserva temporaria = reservas[j];

                    reservas[j] = reservas[j + 1];

                    reservas[j + 1] = temporaria;
                }
            }
        }

        System.out.println("Reservas ordenadas com sucesso!");

        listarReservas();
    }
}
