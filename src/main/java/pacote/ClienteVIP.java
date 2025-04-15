package pacote;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * ClienteVIP herda de Cliente e possui um campo de desconto adicional.
 */
@Entity
public class ClienteVIP extends Cliente {

    @Column(nullable = false)
    private double desconto;

    public ClienteVIP() {
    }

    public ClienteVIP(String nome, String telefone, double desconto) {
        super(nome, telefone);
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}
