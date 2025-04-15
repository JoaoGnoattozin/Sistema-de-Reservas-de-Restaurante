	package pacote;
	
	import javax.persistence.EntityManagerFactory;
	
	public class MainApp {
	    
	    public static void main(String[] args) {
	        // Configuração do Hibernate
	        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
	        
	       // Inicialização dos serviços
	        ClienteService clienteService = new ClienteService(emf);
	        MesaService mesaService = new MesaService(emf);
	        ReservaService reservaService = new ReservaService(emf);
	        
	        // Inicializar dados de teste
	        inicializarDadosTeste(clienteService, mesaService);
	        
	        // Iniciar controlador
	        RestauranteController controller = new RestauranteController(
	            clienteService, mesaService, reservaService);
	        System.out.println(">>>> CHEGOU AQUI! MENU VAI INICIAR...");
	        controller.iniciar();

	        controller.iniciar();
	        
	        // Fechar EntityManagerFactory
	        emf.close();
	    }
	    
	    private static void inicializarDadosTeste(ClienteService clienteService, 
	                                            MesaService mesaService) {
	        // Criar mesas
	        for (int i = 1; i <= 8; i++) {
	            mesaService.salvar(new Mesa(i, 4));
	        }
	        mesaService.salvar(new MesaVIP(9, 6, true));
	        mesaService.salvar(new MesaVIP(10, 4, false));
	        
	        // Criar clientes
	        clienteService.salvar(new Cliente("João Silva", "11987654321"));
	        clienteService.salvar(new ClienteVIP("Maria Souza", "11912345678", 10.0));
	    }
	}
