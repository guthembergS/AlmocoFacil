package br.com.almocofacil.test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author gerson-brandao
 */
public class CartaoCreditoCrudTest extends GenericTest {
    
    @Test
    public void criarCartaoCredito() {
        CartaoCredito cartaocredito = new CartaoCredito();
        cartaocredito.setBandeira("Master");
        cartaocredito.setDataExpiracao(getData(12, 06, 2023));
        cartaocredito.setNumero("2345257889548754");
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 8);
        Cliente cliente = query.getSingleResult();
        assertNotNull(cliente);
        cartaocredito.setDono(cliente);
        // <CARTAO_CREDITO BANDEIRA="VISA" NUMERO="1192828000000200" DT_EXPIRACAO="2020-06-11"/> -->
        em.persist(cartaocredito);
        em.flush();
        
        TypedQuery<CartaoCredito> queryCat = em.createNamedQuery("CartaoCredito.PorNumero", CartaoCredito.class);
        //queryCat.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCat.setParameter("numero", "2345257889548754");
        
        assertNotNull(queryCat.getSingleResult());
    }

    @Test
    public void atualizarCartaoCreditoMerge() {
        logger.info("Executando atualizarPratoMerge()");
        //TypedQuery<CartaoCredito> query = em.createQuery("SELECT c FROM CartaoCredito c WHERE c.idCartaoCredito = ?1", CartaoCredito.class);
        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 7);
        CartaoCredito cartaocredito = query.getSingleResult();
        assertNotNull(cartaocredito);
        String bandeira = "AmericanExpress";
        String numero = "0987890065488763";
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setNumero(numero);

        em.clear();
        em.merge(cartaocredito);
        em.flush();
        
        /* query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cartaocredito = query.getSingleResult();
        assertEquals(bandeira, cartaocredito.getBandeira());
        assertEquals(numero, cartaocredito.getNumero());
        */
        assertEquals(bandeira, cartaocredito.getBandeira());
        assertEquals(numero, cartaocredito.getNumero());
        
    }

    @Test
    public void removerCartaoCredito() {
    
        TypedQuery<CartaoCredito> query = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 8);
        CartaoCredito cartaocredito = query.getSingleResult();
        assertNotNull(cartaocredito);
        
        em.remove(cartaocredito);
        em.flush();
        
        assertEquals(0,query.getResultList().size());
    
    }
    
    //teste de nativeQuery, retorna cartões por bandeira.
    @Test
    public void retornarNativeQuery(){
        Query query;
        query = em.createNamedQuery("CartaoCredito.PorBandeiraSQL");
        query.setParameter(1, "VISA");
        List<CartaoCredito> cartoes = query.getResultList();
        assertEquals(4, cartoes.size());
    }
}
