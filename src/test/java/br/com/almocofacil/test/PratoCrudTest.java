package br.com.almocofacil.test;

import br.com.almocofacil.model.Prato;
import br.com.almocofacil.model.Vendedor;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PratoCrudTest extends GenericTest {
    
    
    @Test
    public void persistirPrato() {
        logger.info("Executando persistirPrato()");
        Prato prato = em.find(Prato.class, new Long(1));
        Prato novoPrato = new Prato();
        novoPrato.setNmPrato("Salada com Bacalhau");
        novoPrato.setValor(14.00);
        
        novoPrato.setVendedor(retornaVendedor());
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
        Prato pratoRemov = retornaPratoPorNome("Salada com Bacalhau");
        assertNotNull(pratoRemov);
        em.remove(pratoRemov);
        em.flush();
        assertEquals(0,retornaPratosPorNome("Salada com Bacalhau").size());
    }
    
    private Prato retornaPrato(int id){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.idPrato = ?1",
                Prato.class);
        query.setParameter(1, id); //Setando parâmetro posicional.
        Prato prato = query.getSingleResult();
        return prato;
    }
    
    private Prato retornaPratoPorNome(String nmPrato){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.nmPrato = ?1",
                Prato.class);
        query.setParameter(1, nmPrato); //Setando parâmetro posicional.
        Prato prato = query.getSingleResult();
        return prato;
    }
    
    private List<Prato> retornaPratosPorNome(String nmPrato){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.nmPrato = ?1",
                Prato.class);
        query.setParameter(1, nmPrato); //Setando parâmetro posicional.
        List<Prato> pratos = query.getResultList();
        return pratos;
    }
    
    private Vendedor retornaVendedor(){
        TypedQuery<Vendedor> query;
        query = em.createQuery(
                "SELECT c FROM Vendedor c WHERE c.idUsuario = ?1",
                Vendedor.class);
        query.setParameter(1, 1); //Setando parâmetro posicional.
        Vendedor usu = query.getSingleResult();
        return usu;
    }
    
    
}
