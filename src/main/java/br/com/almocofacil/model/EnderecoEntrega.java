package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
                    query = "SELECT e FROM EnderecoEntrega e WHERE e.idEnderecoEntrega = ?id"
            )
        }
        
)
public class EnderecoEntrega implements Serializable {
    @Id
    @Column(name = "ID_ENDERECO_ENTREGA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idEnderecoEntrega;
    
    @Column(name = "LOGADOURO")
    protected String logadouro;
    
    @Column(name = "BAIRRO")
    protected String bairro;

    @Column(name = "CIDADE")
    protected String cidade;

    @Column(name = "ESTADO")
    protected String estado;

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
    
}
