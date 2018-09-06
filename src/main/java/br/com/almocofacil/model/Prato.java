package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name = "PRATO")
public class Prato  implements Serializable {
    
    @Id
    @Column(name = "ID_PRATO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idPrato;
    
    @Column(name = "NM_PRATO")
    protected String nmPrato;
    
    @Column(name = "QUANTIDADE_PESO")
    protected Long quantidadePeso;

    @Column(name = "VALOR")
    protected Double valor;
    
    //criar enum com o tipo de prato

    public void setNmPrato(String nmPrato) {
        this.nmPrato = nmPrato;
    }

    public void setQuantidadePeso(Long quantidadePeso) {
        this.quantidadePeso = quantidadePeso;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNmPrato() {
        return nmPrato;
    }

    public Long getQuantidadePeso() {
        return quantidadePeso;
    }

    public Double getValor() {
        return valor;
    }
    
}
