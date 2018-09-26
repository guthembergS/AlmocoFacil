package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name="EMPRESA")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Empresa.PorId",
                    query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :id"
            )
        }
        
)
public class Empresa implements Serializable {
    
    @Id
    @Column(name = "ID_EMPRESA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idEmpresa;
    
    @Column(name = "NM_EMPRESA")
    protected String nmEmpresa;
    
    @Column(name = "CNPJ")
    protected String cnpj;

    @Column(name = "TELEFONE")
    protected String telefone;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_ENDERECO_ENTREGA", referencedColumnName = "ID_ENDERECO_ENTREGA")
    private EnderecoEntrega enderecoEntrega;

    @OneToMany(mappedBy = "empresa",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Cliente> clientes;

    public String getNmEmpresa() {
        return nmEmpresa;
    }

    public void setNmEmpresa(String nmEmpresa) {
        this.nmEmpresa = nmEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public boolean setClientes(Cliente clientes) {
        //this.clientes = clientes;
        return this.clientes.add(clientes);
        
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }
    
    
    
}
