package pacote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class MesaService {

    private EntityManagerFactory emf;

    public MesaService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void salvar(Mesa mesa) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mesa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Optional<Mesa> buscarPorNumero(int numero) {
        EntityManager em = emf.createEntityManager();
        try {
            Mesa mesa = em.createQuery("SELECT m FROM Mesa m WHERE m.numero = :numero", Mesa.class)
                    .setParameter("numero", numero)
                    .getSingleResult();
            return Optional.ofNullable(mesa);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public List<Mesa> listarDisponiveis() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Mesa m WHERE m.ocupada = false", Mesa.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}
