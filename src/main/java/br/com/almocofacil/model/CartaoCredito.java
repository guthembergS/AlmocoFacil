package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CARTAO_CREDITO")
public class CartaoCredito implements Serializable{
    
    @Id
    @Column(name = "ID_CARTAO_CREDITO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCartaoCredito;
    
    @Column(name = "BANDEIRA")
    protected String bandeira;
    
    @Column(name = "NUMERO")
    protected String numero;
    
    @Column(name = "NOME_PROPRIETARIO")
    protected String nomeProprietario;
    
    @Column(name = "CPF_PROPRIETARIO")
    protected String cpfProprietario;

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBandeira() {
        return bandeira;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public Long getIdCartaoCredito() {
        return idCartaoCredito;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public String getNumero() {
        return numero;
    }
    
    
    
    
    
}
