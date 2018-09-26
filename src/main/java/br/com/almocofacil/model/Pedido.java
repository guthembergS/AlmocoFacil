package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PEDIDO")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Pedido.PorId",
                    query = "SELECT p FROM Pedido p WHERE p.idPedido = :id"
            ),
            @NamedQuery(
                    name = "Pedido.PorIdCliente",
                    query = "SELECT p FROM Pedido p WHERE p.cliente.idUsuario = :id"
            ),
            @NamedQuery(
                    name = "Pedido.PorIdVendedor",
                    query = "SELECT p FROM Pedido p WHERE p.vendedor.idUsuario = :id"
            )
        }
)

public class Pedido implements Serializable {
    
    @Id
    @Column(name = "ID_PEDIDO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idPedido;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_PEDIDO", nullable = false)
    protected Date dtPedido;
    
    @Column(name="VL_TOTAL")
    protected Double vlTotal;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO", nullable = false)
    protected Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="PEDIDO_PRATO",joinColumns = {
        @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO", nullable = false)},
            inverseJoinColumns = { 
                 @JoinColumn(name = "ID_PRATO", referencedColumnName = "ID_PRATO", nullable = false)
            }
    )
    protected List<Prato> pratos;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID_USUARIO", nullable = false)
    protected Vendedor vendedor;
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public Date getDtPedido() {
        return dtPedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public Double getVlTotal() {
        return vlTotal;
    }
    
}
