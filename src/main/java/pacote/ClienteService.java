package pacote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ClienteService {

    private EntityManagerFactory emf;

    public ClienteService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Cliente salvar(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cliente;
    }
}
