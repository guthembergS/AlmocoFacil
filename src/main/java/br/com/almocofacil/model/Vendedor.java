package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@DiscriminatorValue(value = "V")
//@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Vendedor.PorId",
                    query = "SELECT c FROM Vendedor c WHERE c.idUsuario = :id"
            )
        }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Vendedor.PorIdSQL",
                    query = "SELECT ID_USUARIO, email, nome, senha FROM TB_USUARIO WHERE ID_USUARIO = ? ",
                    resultClass = Vendedor.class
            )
        }
)

public class Vendedor extends Usuario implements Serializable {
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Prato> pratos = new ArrayList<>();
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)    
    protected List<Pedido> pedidos = new ArrayList<>();        

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
