package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author guthemberg
 */

@Entity
@DiscriminatorValue(value = "C")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Cliente.PorId",
                    query = "SELECT c FROM Cliente c WHERE c.idUsuario = :id"
            ),
            @NamedQuery(
                    name = "Cliente.PorNome",
                    query = "SELECT c FROM Cliente c WHERE c.nome = :nome"
            )
        }
)

@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Cliente.PorIdSQL",
                    query = "SELECT c.id_usuario, c.email, c.nome, c.senha, c.ID_EMPRESA, c.ID_CARTAO_CREDITO"
                               + " FROM TB_USUARIO c "
                               + " WHERE c.ID_USUARIO = ? ",
                    resultClass = Cliente.class
            ),
            
            @NamedNativeQuery(
                    name = "Cliente.PorNomeSQL",
                    query = " SELECT c.ID_USUARIO, c.email, c.nome, c.senha, c.ID_EMPRESA, c.ID_CARTAO_CREDITO"
                    + " FROM TB_USUARIO c  "
                    + " WHERE c.nome = ? ",
                    resultClass = Cliente.class
            )
                                                              
        }
)

//@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Cliente extends Usuario implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    protected Empresa empresa;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = true)
    @JoinColumn(name = "ID_CARTAO_CREDITO", referencedColumnName = "ID_CARTAO_CREDITO")
    protected CartaoCredito cartaoCredito;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Pedido> pedidos = new ArrayList<Pedido>();

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean setPedidos(Pedido pedido) {
        return this.pedidos.add(pedido);
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }
}
