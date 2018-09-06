package br.com.almocofacil.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    //tp_pagamento enum
    
    //lista de pratos
    
    //endereco de entrega

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
