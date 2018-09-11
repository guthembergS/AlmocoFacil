package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CARTAO_CREDITO")
public class CartaoCredito implements Serializable{
    
    @Id
    @Column(name = "ID_CARTAO_CREDITO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCartaoCredito;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cartaoCredito", optional = false)
    private Cliente dono;
    
    @Column(name = "BANDEIRA", nullable = false, length = 100)
    private String bandeira;
    
    @Column(name = "NUMERO", nullable = false, length = 30)
    private String numero;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_EXPIRACAO", nullable = false)
    private Date dataExpiracao;

    public String getBandeira() {
        return bandeira;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public Cliente getDono() {
        return dono;
    }

    public String getNumero() {
        return numero;
    }

    public Long getIdCartaoCredito() {
        return idCartaoCredito;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
