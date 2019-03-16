package br.com.almocofacil.test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;

/**
 *
 * @author gerson-brandao
 */
public class CartaoCreditoCrudTest extends GenericTest {

    @Test
    public void criarCartaoCredito() {
        logger.info("Executando criarCartaoCredito()");
        
        String bandeira = "VISA";
        String numeroCartao = "090928938495856";
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
        String novaBandeira = "AmericanExpress";
        String novoNumero = "0987890065488763";

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

        long idCartao = 9;
        String novaBandeira = "AmericanExpress";
        String novoNumero = "0987890065488763";

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

        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idCartao);
        CartaoCredito cartaocredito = query.getSingleResult();

        assertNotNull(cartaocredito);

        em.remove(cartaocredito);
        em.flush();

        assertEquals(0, query.getResultList().size());

    }

}
