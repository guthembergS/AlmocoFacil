package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name = "TB_CLIENTE")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Cliente extends Usuario implements Serializable {
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false)
    protected Empresa empresa;
    
    /*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    protected List<CartaoCredito> cartaoCredito;
*/
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /*
    public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public List<CartaoCredito> getCartaoCredito() {
        return cartaoCredito;
    }*/
}
