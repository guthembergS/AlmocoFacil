package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author guthemberg
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
        cartaocredito.setDono(retornaCliente(8));
        // <CARTAO_CREDITO BANDEIRA="VISA" NUMERO="1192828000000200" DT_EXPIRACAO="2020-06-11"/> -->
        cartaocredito.setNumero("2345257889548754");
        return cartaocredito;
    }

    @Test
    public void atualizarCartaoCredito() {
        logger.info("Executando atualizarCartaoCredito()");
        CartaoCredito cartaocredito = retornaCartaoCredito(8);
        assertNotNull(cartaocredito);

        String bandeira = "AmericanExpress";
        String numero = "0987890065488763";
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setNumero(numero);
        em.flush();
        cartaocredito = retornaCartaoCredito(8);
        assertEquals(bandeira, cartaocredito.getBandeira());
        assertEquals(numero, cartaocredito.getNumero());
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

}
