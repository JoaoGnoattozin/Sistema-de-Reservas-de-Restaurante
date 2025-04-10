package com.restaurante.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class ClienteVIP extends Cliente {
    
    private double desconto;
    
    public ClienteVIP() {}
    
    public ClienteVIP(String nome, String telefone, double desconto) {
        super(nome, telefone);
        this.desconto = desconto;
    }
}
