package com.restaurante.repository;

import com.restaurante.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    // Consulta derivada
    List<Reserva> findByClienteNomeContaining(String nome);
    
    // Consulta com JOIN
    @Query("SELECT r FROM Reserva r JOIN r.mesa m WHERE m.numero = :numeroMesa")
    List<Reserva> findByNumeroMesa(@Param("numeroMesa") Integer numeroMesa);
    
    // Consulta com intervalo de datas
    @Query("SELECT r FROM Reserva r WHERE r.horario BETWEEN :inicio AND :fim")
    List<Reserva> findByPeriodo(@Param("inicio") LocalDateTime inicio, 
                               @Param("fim") LocalDateTime fim);
    
    // Consulta com LIKE
    @Query("SELECT r FROM Reserva r WHERE r.cliente.nome LIKE %:termo%")
    List<Reserva> findByNomeClienteParcial(@Param("termo") String termo);
    
    // Consulta agregada
    @Query("SELECT COUNT(r), AVG(m.capacidade) FROM Reserva r JOIN r.mesa m WHERE r.status = 'CONFIRMADA'")
    Object[] countReservasAndAvgCapacidade();
}
