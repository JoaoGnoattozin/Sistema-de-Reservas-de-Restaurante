package pacote;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * MesaVIP herda de Mesa e adiciona o campo "temVistaExclusiva".
 */
@Entity
public class MesaVIP extends Mesa {

    @Column(nullable = false)
    private Boolean temVistaExclusiva;

    public MesaVIP() {
    }

    public MesaVIP(Integer numero, Integer capacidade, Boolean temVistaExclusiva) {
        super(numero, capacidade);
        this.temVistaExclusiva = temVistaExclusiva;
    }

    public Boolean getTemVistaExclusiva() {
        return temVistaExclusiva;
    }

    public void setTemVistaExclusiva(Boolean temVistaExclusiva) {
        this.temVistaExclusiva = temVistaExclusiva;
    }
}
