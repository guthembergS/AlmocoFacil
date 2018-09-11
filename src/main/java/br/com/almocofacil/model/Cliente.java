package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA", nullable = false)
    protected Empresa empresa;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name = "ID_CARTAO_CREDITO", referencedColumnName = "ID_CARTAO_CREDITO", nullable = false)
    protected CartaoCredito cartaoCredito;
    
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Pedido> pedidos;

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
        
    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }
}
