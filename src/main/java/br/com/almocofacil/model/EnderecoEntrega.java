package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name = "ENDERECO_ENTREGA")
@NamedQueries(
        {
            @NamedQuery(
                    name = "EnderecoEntrega.PorId",
                    query = "SELECT e FROM EnderecoEntrega e WHERE e.idEnderecoEntrega = :id"
            )
        }
)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "EnderecoEntrega.PorIdSQL",
                    query = "SELECT E.ID_ENDERECO_ENTREGA,E.BAIRRO,E.CEP,E.CIDADE,E.ESTADO,E.LOGADOURO "
                    + "FROM ENDERECO_ENTREGA E "
                    + "WHERE E.ID_ENDERECO_ENTREGA = ?",
                    resultClass = EnderecoEntrega.class
            ),
            @NamedNativeQuery(
                    name = "EnderecoEntrega.PorBairroSQL",
                    query = "SELECT E.ID_ENDERECO_ENTREGA,E.BAIRRO,E.CEP,E.CIDADE,E.ESTADO,E.LOGADOURO "
                    + "FROM ENDERECO_ENTREGA E "
                    + "WHERE UPPER(E.BAIRRO) LIKE UPPER(?)",
                    resultClass = EnderecoEntrega.class
            )
        }
)

public class EnderecoEntrega implements Serializable {

    @Id
    @Column(name = "ID_ENDERECO_ENTREGA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idEnderecoEntrega;
        
    @NotNull
    @Column(name = "LOGADOURO")
    protected String logadouro;

    @NotNull
    @Column(name = "BAIRRO")
    protected String bairro;
    
    @NotNull
    @Column(name = "CIDADE")
    protected String cidade;
    
    @NotNull
    @Column(name = "ESTADO")
    protected String estado;

    @Size (max = 8)
    @Column(name = "CEP")
    protected String cep;

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public long getId() {
        return idEnderecoEntrega;
    }

}
