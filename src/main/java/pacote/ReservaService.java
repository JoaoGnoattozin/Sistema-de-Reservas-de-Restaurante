package pacote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {

    private EntityManagerFactory emf;

    public ReservaService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Reserva fazerReserva(Reserva reserva) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
            return reserva;
        } finally {
            em.close();
        }
    }

    public void cancelarReserva(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Reserva reserva = em.find(Reserva.class, id);
            if (reserva != null) {
                reserva.setStatus(StatusReserva.CANCELADA);
                em.merge(reserva);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Reserva> listarReservas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Reserva> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Reserva r WHERE r.horario BETWEEN :inicio AND :fim", Reserva.class)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getResultList();
        } finally {
            em.close();
        }
    }
}
