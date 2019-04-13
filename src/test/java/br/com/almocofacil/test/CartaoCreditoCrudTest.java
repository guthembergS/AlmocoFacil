package br.com.almocofacil.test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import javax.persistence.Query;

/**
 *
 * @author gerson-brandao
 */
public class CartaoCreditoCrudTest extends GenericTest {

    @Test
    public void criarCartaoCredito() {
        logger.info("Executando criarCartaoCredito()");

        String bandeira = "VISA";
        String numeroCartao = "4539707770653196";
        long idCliente = 8;

        CartaoCredito cartaocredito = new CartaoCredito();
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setDataExpiracao(getData(12, 01, 2025));
        cartaocredito.setNumero(numeroCartao);

        //Busca o nome do dono do cartão
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idCliente);
        Cliente cliente = query.getSingleResult();

        //Verifica de retornou algo
        assertNotNull(cliente);
        cartaocredito.setDono(cliente);

        em.persist(cartaocredito);
        em.flush();

        TypedQuery<CartaoCredito> queryCartao = em.createNamedQuery("CartaoCredito.PorNumero", CartaoCredito.class);
        queryCartao.setParameter("numero", numeroCartao);

        assertNotNull(queryCartao.getSingleResult());

    }

    @Test
    public void atualizarCartaoCredito() {
        logger.info("Executando atualizarCartaoCredito()");

        long idCartao = 9;
        String novaBandeira = "VISA";
        String novoNumero = "4539669152193672";

        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idCartao);
        CartaoCredito cartaocredito = query.getSingleResult();

        assertNotNull(cartaocredito);

        cartaocredito.setBandeira(novaBandeira);
        cartaocredito.setNumero(novoNumero);

        em.flush();

        //Executa a query para confirmar a atualização
        cartaocredito = query.getSingleResult();

        assertEquals(novaBandeira, cartaocredito.getBandeira());
        assertEquals(novoNumero, cartaocredito.getNumero());

    }

    @Test
    public void atualizarCartaoCreditoMerge() {
        logger.info("Executando atualizarCartaoCreditoMerge()");

        long idCartao = 7;
        String novaBandeira = "VISA";
        String novoNumero = "4556443417804160";

        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idCartao);
        CartaoCredito cartaocredito = query.getSingleResult();

        assertNotNull(cartaocredito);

        cartaocredito.setBandeira(novaBandeira);
        cartaocredito.setNumero(novoNumero);

        em.clear();
        em.merge(cartaocredito);
        em.flush();

        //Executa a query para confirmar a atualização
        cartaocredito = query.getSingleResult();

        assertEquals(novaBandeira, cartaocredito.getBandeira());
        assertEquals(novoNumero, cartaocredito.getNumero());

    }

    @Test
    public void removerCartaoCredito() {
        logger.info("Executando removerCartaoCredito()");

        long idCartao = 8;

        Query cartaoNativeQuery = em.createNamedQuery("CartaoCredito.PorIdSQL");
        cartaoNativeQuery.setParameter(1, idCartao);
        CartaoCredito cartaoCreditoNative = (CartaoCredito) cartaoNativeQuery.getSingleResult();

        assertNotNull(cartaoCreditoNative);

        assertNotNull(cartaoCreditoNative);

        em.remove(cartaoCreditoNative);
        em.flush();

        assertEquals(0, cartaoNativeQuery.getResultList().size());

    }

    @Test
    public void criarCartaoCreditoNative() {
        logger.info("Executando criarCartaoCreditoNative()");

        String bandeira = "American Express";
        String numeroCartao = "341385790782893";
        long idCliente = 15;

        CartaoCredito cartaocredito = new CartaoCredito();
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setDataExpiracao(getData(13, 02, 2020));
        cartaocredito.setNumero(numeroCartao);

        //Busca o nome do dono do cartão
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorIdSQL", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, idCliente);
        Cliente cliente = query.getSingleResult();

        //Verifica de retornou algo
        assertNotNull(cliente);
        cartaocredito.setDono(cliente);

        em.persist(cartaocredito);
        em.flush();

        TypedQuery<CartaoCredito> queryCartao = em.createNamedQuery("CartaoCredito.PorNumeroSQL", CartaoCredito.class);
        queryCartao.setParameter(1, numeroCartao);

        assertNotNull(queryCartao.getSingleResult());

    }
    
    @Test
    public void atualizarCartaoCreditoNativeQueryID() {
        logger.info("Executando atualizarCartaoCreditoNativeQueryID()");

        long idCartao = 7;
        String novaBandeira = "AmericanExpress";
        String novoNumero = "377081601204287";
                
        Query cartaoNativeQuery = em.createNamedQuery("CartaoCredito.PorIdSQL");
        cartaoNativeQuery.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cartaoNativeQuery.setParameter(1, idCartao);
        CartaoCredito cartaoCreditoNative = (CartaoCredito) cartaoNativeQuery.getSingleResult();
        
        assertNotNull(cartaoCreditoNative);
               
        cartaoCreditoNative.setBandeira(novaBandeira);
        cartaoCreditoNative.setNumero(novoNumero);

        em.flush();
        
        cartaoCreditoNative = (CartaoCredito) cartaoNativeQuery.getSingleResult();

        assertEquals(novaBandeira, cartaoCreditoNative.getBandeira());
        assertEquals(novoNumero, cartaoCreditoNative.getNumero());

    }
    
    @Test
    public void atualizarCartaoCreditoNativeQueryNumeroMerge() {
        logger.info("Executando atualizarCartaoCreditoNativeQueryNumero()");

        long idCartao = 7;
        String novaBandeira = "Hiper";
        String numeroAntigo = "5143534259382518";
        String novoNumero = "5183622687235456";
                
        Query cartaoNativeQuery = em.createNamedQuery("CartaoCredito.PorNumeroSQL");
        cartaoNativeQuery.setParameter(1, numeroAntigo);
        CartaoCredito cartaoCreditoNative = (CartaoCredito) cartaoNativeQuery.getSingleResult();
        
        assertNotNull(cartaoCreditoNative);
               
        cartaoCreditoNative.setBandeira(novaBandeira);
        cartaoCreditoNative.setNumero(novoNumero);

        em.clear();
        em.merge(cartaoCreditoNative);
        em.flush();
        
        cartaoNativeQuery.setParameter(1, novoNumero);
        cartaoCreditoNative = (CartaoCredito) cartaoNativeQuery.getSingleResult();

        assertEquals(novaBandeira, cartaoCreditoNative.getBandeira());
        assertEquals(novoNumero, cartaoCreditoNative.getNumero());

    }    
    
    @Test
    public void removerCartaoCreditoNativeQueryID() {
        logger.info("Executando removerCartaoCredito()");

        long idCartao = 11;

        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorIdSQL", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, idCartao);
        CartaoCredito cartaocredito = query.getSingleResult();

        assertNotNull(cartaocredito);

        em.remove(cartaocredito);
        em.flush();

        assertEquals(0, query.getResultList().size());

    }
     
}
