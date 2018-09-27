package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */

@Entity
@Table(name = "TB_VENDEDOR")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Vendedor.PorId",
                    query = "SELECT c FROM Vendedor c WHERE c.idUsuario = :id"
            )
        }
)
public class Vendedor extends Usuario implements Serializable {
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Prato> pratos;
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)    
    protected List<Pedido> pedidos;        

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean setPedidos(Pedido pedidos) {
        return this.pedidos.add(pedidos);
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public boolean setPratos(Prato pratos) {
        return this.pratos.add(pratos);
    }

}
