package br.com.almocofacil.test;

import br.com.almocofacil.model.Prato;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PratoCrudTest extends GenericTest {
    
    /*
    @Test
    public void persistirPrato() {
        logger.info("Executando persistirPrato()");
        Prato novoPrato = new Prato();
        novoPrato.setNmPrato("Salada com Bacalhau");
        novoPrato.setValor(14.00);
        
        novoPrato.setVendedor(retornaVendedor(1));
        em.persist(novoPrato);
        em.flush();
        assertNotNull(novoPrato.getIdPrato());
    }
    
    @Test
    public void atualizarPrato() {
        logger.info("Executando atualizarPrato()");       
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
        Prato pratoRemov = retornaPratoPorNome("Sushi");
        assertNotNull(pratoRemov);
        em.remove(pratoRemov);
        em.flush();
        assertEquals(0,retornaPratosPorNome("Sushi").size());
    }
    */
}
