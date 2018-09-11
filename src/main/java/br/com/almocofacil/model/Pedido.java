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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "PEDIDO")
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
    
    @Column(name = "OBSERVACAO")
    protected String observacao;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO", nullable = false)
    protected Cliente cliente;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Prato> pratos;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID_VENDEDOR", nullable = false)
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

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public Double getVlTotal() {
        return vlTotal;
    }
    
    
    
}
