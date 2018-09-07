package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name = "TB_CLIENTE")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Cliente extends Usuario implements Serializable {
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    protected List<EnderecoEntrega> endereco;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    protected List<CartaoCredito> cartaoCredito;

    public void setEndereco(List<EnderecoEntrega> endereco) {
        this.endereco = endereco;
    }

    public List<EnderecoEntrega> getEndereco() {
        return endereco;
    }    
    
    public void setCartaoCredito(List<CartaoCredito> cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public List<CartaoCredito> getCartaoCredito() {
        return cartaoCredito;
    }
}
