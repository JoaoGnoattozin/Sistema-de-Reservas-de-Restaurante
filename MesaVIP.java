package com.restaurante.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class MesaVIP extends Mesa {
    
    private Boolean temVistaExclusiva;
    
    public MesaVIP() {}
    
    public MesaVIP(Integer numero, Integer capacidade, Boolean temVistaExclusiva) {
        super(numero, capacidade);
        this.temVistaExclusiva = temVistaExclusiva;
    }
}
