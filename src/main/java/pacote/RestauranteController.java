package pacote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import pacote.Cliente;
import pacote.ClienteVIP;
import pacote.Mesa;
import pacote.MesaVIP;
import pacote.Reserva;
import pacote.ReservaService;
import pacote.StatusReserva;
import pacote.ClienteService;
import pacote.MesaService;

public class RestauranteController {
    
    private final Scanner scanner;
    private final ClienteService clienteService;
    private final MesaService mesaService;
    private final ReservaService reservaService;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public RestauranteController(ClienteService clienteService, 
                               MesaService mesaService, 
                               ReservaService reservaService) {
        this.scanner = new Scanner(System.in);
        this.clienteService = clienteService;
        this.mesaService = mesaService;
        this.reservaService = reservaService;
    }
    
    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 6);
    }
    
    private void exibirMenu() {
        System.out.println("\n=== SISTEMA DE RESERVAS ===");
        System.out.println("1. Fazer reserva");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Listar reservas");
        System.out.println("4. Listar mesas disponíveis");
        System.out.println("5. Consultar reservas por período");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        return scanner.nextInt();
    }
    
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                fazerReserva();
                break;
            case 2:
                cancelarReserva();
                break;
            case 3:
                listarReservas();
                break;
            case 4:
                listarMesasDisponiveis();
                break;
            case 5:
                consultarReservasPorPeriodo();
                break;
            case 6:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void fazerReserva() {
        scanner.nextLine(); // Limpar buffer
        
        System.out.println("\n--- NOVA RESERVA ---");
        
        // Cadastrar cliente
        Cliente cliente = cadastrarCliente();
        
        // Listar mesas disponíveis
        listarMesasDisponiveis();
        
        // Selecionar mesa
        System.out.print("Número da mesa: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();
        
        // Data e horário
        System.out.print("Data e horário (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime horario = LocalDateTime.parse(dataStr, formatter);
        
        try {
            Mesa mesa = mesaService.buscarPorNumero(numeroMesa)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));
            
            Reserva reserva = new Reserva(cliente, mesa, horario);
            reservaService.fazerReserva(reserva);
            System.out.println("Reserva realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao fazer reserva: " + e.getMessage());
        }
    }
    
    private Cliente cadastrarCliente() {
        System.out.println("\nDados do cliente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone (11 dígitos): ");
        String telefone = scanner.nextLine();
        
        System.out.print("É cliente VIP? (S/N): ");
        String vip = scanner.nextLine();
        
        if (vip.equalsIgnoreCase("S")) {
            System.out.print("Desconto VIP (%): ");
            double desconto = scanner.nextDouble();
            scanner.nextLine();
            
            return clienteService.salvar(new ClienteVIP(nome, telefone, desconto));
        } else {
            return clienteService.salvar(new Cliente(nome, telefone));
        }
    }
    
    private void cancelarReserva() {
        scanner.nextLine();
        
        System.out.println("\n--- CANCELAR RESERVA ---");
        System.out.print("ID da reserva: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        
        try {
            reservaService.cancelarReserva(id);
            System.out.println("Reserva cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar reserva: " + e.getMessage());
        }
    }
    
    private void listarReservas() {
        System.out.println("\n--- LISTA DE RESERVAS ---");
        reservaService.listarReservas().forEach(this::exibirReserva);
    }
    
    private void exibirReserva(Reserva reserva) {
        System.out.printf("ID: %d | Cliente: %s | Mesa: %d | Horário: %s | Status: %s%n",
            reserva.getId(),
            reserva.getCliente().getNome(),
            reserva.getMesa().getNumero(),
            reserva.getHorario().format(formatter),
            reserva.getStatus());
    }
    
    private void listarMesasDisponiveis() {
        System.out.println("\n--- MESAS DISPONÍVEIS ---");
        mesaService.listarDisponiveis().forEach(mesa -> {
            System.out.printf("Mesa %d | Capacidade: %d", 
                mesa.getNumero(), mesa.getCapacidade());
            if (mesa instanceof MesaVIP) {
                System.out.print(" | VIP");
            }
            System.out.println();
        });
    }
    
    private void consultarReservasPorPeriodo() {
        scanner.nextLine();
        
        System.out.println("\n--- CONSULTAR POR PERÍODO ---");
        System.out.print("Data inicial (dd/MM/yyyy HH:mm): ");
        LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine(), formatter);
        
        System.out.print("Data final (dd/MM/yyyy HH:mm): ");
        LocalDateTime fim = LocalDateTime.parse(scanner.nextLine(), formatter);
        
        System.out.println("\n--- RESERVAS NO PERÍODO ---");
        reservaService.buscarPorPeriodo(inicio, fim).forEach(this::exibirReserva);
    }
}
