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
@Table(name = "TB_VENDEDOR")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Vendedor extends Usuario implements Serializable {
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Prato> pratos;
    
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Pedido> pedidos;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
    
}
