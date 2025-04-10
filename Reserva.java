package com.restaurante.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;
    
    @Column(nullable = false)
    private LocalDateTime horario;
    
    @Enumerated(EnumType.STRING)
    private StatusReserva status;
    
    public Reserva() {}
    
    public Reserva(Cliente cliente, Mesa mesa, LocalDateTime horario) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.horario = horario;
        this.status = StatusReserva.CONFIRMADA;
    }
}
