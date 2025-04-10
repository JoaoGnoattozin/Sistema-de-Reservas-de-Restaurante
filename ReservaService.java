package com.restaurante.service;

import com.restaurante.model.Reserva;
import com.restaurante.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {
    
    private final ReservaRepository reservaRepository;
    
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    
    @Transactional
    public Reserva fazerReserva(Reserva reserva) {
        // Verifica se a mesa já está reservada no mesmo horário
        List<Reserva> conflitos = reservaRepository.findByMesaAndHorarioBetween(
            reserva.getMesa(),
            reserva.getHorario().minusHours(2),
            reserva.getHorario().plusHours(2)
        );
        
        if (!conflitos.isEmpty()) {
            throw new IllegalStateException("Mesa já reservada para este horário");
        }
        
        return reservaRepository.save(reserva);
    }
    
    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        
        reserva.setStatus(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);
    }
    
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
    
    public List<Reserva> buscarPorNomeCliente(String nome) {
        return reservaRepository.findByClienteNomeContaining(nome);
    }
    
    public List<Reserva> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return reservaRepository.findByPeriodo(inicio, fim);
    }
}
