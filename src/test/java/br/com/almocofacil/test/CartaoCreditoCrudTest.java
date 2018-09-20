package br.com.almocofacil.test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.almocofacil.model.CartaoCredito;
import static br.com.almocofacil.model.CartaoCredito_.bandeira;
import static br.com.almocofacil.model.CartaoCredito_.dono;
import static br.com.almocofacil.model.CartaoCredito_.numero;
import br.com.almocofacil.model.Prato;

/**
 *
 * @author gerson-brandao
 */
public class CartaoCreditoCrudTest extends GenericTest {
    
    private CartaoCredito criarCartaoCredito() {
        CartaoCredito cartaocredito = new CartaoCredito();
        cartaocredito.setBandeira("Master");
        cartaocredito.setDataExpiracao(getData(12, 06, 2023));
        cartaocredito.setDono(retornaCliente(8));
        // <CARTAO_CREDITO BANDEIRA="VISA" NUMERO="1192828000000200" DT_EXPIRACAO="2020-06-11"/> -->
        cartaocredito.setNumero("2345257889548754");
        return cartaocredito;
    }

    @Test
    public void atualizarCartaoCreditoMerge() {
        logger.info("Executando atualizarPratoMerge()");
        TypedQuery<CartaoCredito> query = em.createQuery("SELECT c FROM CartaoCredito c WHERE c.idCartaoCredito = ?1", CartaoCredito.class);
        //não considerar cache
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 7);
        //Retornar apenas um objeto da consulta
        CartaoCredito cartaocredito = query.getSingleResult();
        assertNotNull(cartaocredito);
        String bandeira = "AmericanExpress";
        String numero = "0987890065488763";
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setNumero(numero);

        em.clear();
        em.merge(cartaocredito);
        em.flush();
        assertEquals(bandeira, cartaocredito.getBandeira());
        assertEquals(numero, cartaocredito.getNumero());
    }

    @Test
    public void removerCartaoCredito() {
        CartaoCredito cartaocredito = retornaCartaoCredito(7);
        assertNotNull(cartaocredito);
        em.remove(cartaocredito);
        em.flush();
        //comparar null
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
