package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "PRATO")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = "Prato.PorNome",
                    query = "SELECT c FROM Prato c WHERE c.nmPrato = :nome"
            ),
            @NamedQuery(
                    name = "Prato.PorId",
                    query = "SELECT c FROM Prato c WHERE c.idPrato = :id"
            ),
            @NamedQuery(
                    name = "Prato.PorIdVendedor",
                    query = "SELECT p FROM Prato p WHERE p.vendedor.idUsuario = :id"
            )   
        }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Prato.PorNomeSQL",
                    query = "SELECT ID_PRATO, NM_PRATO, VALOR, ID_VENDEDOR FROM PRATO WHERE NM_PRATO LIKE ? ORDER BY ID_PRATO",
                    resultClass = Prato.class
            ),
            @NamedNativeQuery(
                    name = "Prato.PorIdSQL",
                    query = "SELECT ID_PRATO, NM_PRATO, VALOR, ID_VENDEDOR FROM PRATO WHERE ID_PRATO LIKE ? ORDER BY ID_PRATO",
                    resultClass = Prato.class
            ),
            @NamedNativeQuery(
                    name = "Prato.PorIdVendedorSQL",
                    query = "SELECT ID_PRATO, NM_PRATO, VALOR, ID_VENDEDOR FROM PRATO WHERE ID_VENDEDOR LIKE ? ORDER BY ID_PRATO",
                    resultClass = Prato.class
            )
        }
)

public class Prato  implements Serializable {
    
    @Id
    @Column(name = "ID_PRATO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idPrato;
    
    @Column(name = "NM_PRATO")
    protected String nmPrato;
   
    @Column(name = "VALOR")
    protected Double valor;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "ID_USUARIO", nullable = false)
    protected Vendedor vendedor;

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Long getIdPrato() {
        return idPrato;
    }
    
    public void setNmPrato(String nmPrato) {
        this.nmPrato = nmPrato;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNmPrato() {
        return nmPrato;
    }

    public Double getValor() {
        return valor;
    }
    
}
