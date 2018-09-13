package br.com.almocofacil.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.test.GenericTest;
import java.util.Date;

/**
 *
 * @author gerson-brandao
 */
public class CartaoCreditoCrudTest extends GenericTest {
    
    @Test
    public void persistirCartao() {
        logger.info("Executando persistirCartao()");
        CartaoCredito cartaocredito = criarCartaoCredito();
        em.persist(cartaocredito);
        em.flush();
        assertNotNull(cartaocredito.getIdCartaoCredito());
        
    }
    
    private CartaoCredito criarCartaoCredito() {
          CartaoCredito cartaocredito = new CartaoCredito();
          cartaocredito.setBandeira("Master");
          cartaocredito.setDataExpiracao(getData(12, 06, 2023));
          cartaocredito.setDono(retornaCliente(3));
          cartaocredito.setNumero("2345257889548754");
        return cartaocredito;
    }
    
     @Test
    public void atualizarCartaoCredito() {
        logger.info("Executando atualizarCartaoCredito()");
        
        Prato pratoUpdate = retornaPrato(1);
        assertNotNull(pratoUpdate);
        pratoUpdate.setNmPrato("Lasanha Bolonhesa");
        em.flush();
        Prato pratoAtual = retornaPrato(1);
        assertEquals("Lasanha Bolonhesa", pratoAtual.getNmPrato());
    }
    
    @Test
    public void atualizarPratoMerge() {
        logger.info("Executando atualizarPratoMerge()");
        
        TypedQuery<Prato> query = em.createQuery("SELECT c FROM Prato c WHERE c.idPrato = ?1",Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 8);
        Prato prato = query.getSingleResult();
        assertNotNull(prato);
        prato.setNmPrato("Lasanha Bolonhesa");
        em.clear();        
        em.merge(prato);
        em.flush();
        assertEquals("Lasanha Bolonhesa", query.getSingleResult().getNmPrato());
        
    }
    
    @Test
    public void removerPrato() {
        Prato pratoRemov = retornaPratoPorNome("Salada com Bacalhau");
        assertNotNull(pratoRemov);
        em.remove(pratoRemov);
        em.flush();
        assertEquals(0,retornaPratosPorNome("Salada com Bacalhau").size());
    }
    
}
