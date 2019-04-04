package br.com.almocofacil.test;

import br.com.almocofacil.model.Prato;
import static br.com.almocofacil.model.Usuario_.idUsuario;
import br.com.almocofacil.model.Vendedor;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PratoCrudTest extends GenericTest {

    @Test
    public void persistirPrato() {
        logger.info("Executando persistirPrato()");

        long idVendedor = 11;

        Prato novoPrato = new Prato();
        novoPrato.setNmPrato("Lasanha de Bacalhau");
        novoPrato.setValor(15.00);

        TypedQuery<Vendedor> query = em.createNamedQuery("Vendedor.PorId", Vendedor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idVendedor);
        Vendedor vendedor = (Vendedor) query.getSingleResult();
        assertNotNull(vendedor);
        novoPrato.setVendedor(vendedor);
       
        em.persist(novoPrato);
        em.flush();

        assertNotNull(novoPrato.getIdPrato());

    }

    @Test
    public void atualizarPrato() {
        logger.info("Executando atualizarPrato()");

        long idPrato = 1;
        String novoNome = "Lasanha Bolonhesa";

        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idPrato);
        Prato pratoUpdate = query.getSingleResult();

        assertNotNull(pratoUpdate);
        pratoUpdate.setNmPrato("Lasanha Bolonhesa");

        pratoUpdate.setNmPrato(novoNome);

        em.flush();

        pratoUpdate = query.getSingleResult();
        assertEquals(novoNome, pratoUpdate.getNmPrato());
    }
    
    @Test
    public void atualizarPratoMerge() {
        logger.info("Executando atualizarPratoMerge()");

        long idPrato = 1;
        String novoNome = "Bacalhoada";

        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idPrato);
        Prato pratoUpdate = query.getSingleResult();

        assertNotNull(pratoUpdate);

        pratoUpdate.setNmPrato(novoNome);

        em.clear();
        em.merge(pratoUpdate);
        em.flush();

        assertEquals(novoNome, query.getSingleResult().getNmPrato());

    }

    @Test
    public void removerPrato() {
        logger.info("Executando removerPrato()");
        
        String nomePrato = "Sashimi";
        
        TypedQuery<Prato> query_prato = em.createNamedQuery("Prato.PorNome", Prato.class);
        query_prato.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query_prato.setParameter("nome", nomePrato);
        logger.info(query_prato.toString());
        Prato pratoRemov = query_prato.getSingleResult();

        assertNotNull(pratoRemov);

        Vendedor vendedorAtual = (Vendedor) pratoRemov.getVendedor();
        
        long idVendedor = vendedorAtual.getIdUsuario();

        em.remove(pratoRemov);
        em.flush();

        TypedQuery<Prato> queryPratos = em.createNamedQuery("Prato.PorIdVendedor", Prato.class);
        queryPratos.setParameter("id", idVendedor);
        List<Prato> pratos = queryPratos.getResultList();

        boolean tem_comida = false;

        for (int i = 0; i > pratos.size(); i++) {
            if (pratos.get(i).getNmPrato().equals("Sashimi")) {
                tem_comida = true;
            }
        }

        assertEquals(tem_comida, false);

    }
    
//    @Test
//    public void persistirPratoNativeQuery() {
//        logger.info("Executando persistirPrato()");
//        long id_vendedor = 4;
//        Prato novoPrato = new Prato();
//        novoPrato.setNmPrato("Lasanha de Bacalhau");
//        novoPrato.setValor(15.00);
//
//        Query vendedor_native = em.createNamedQuery("Vendedor.PorIdSQL");
//        vendedor_native.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        vendedor_native.setParameter(1, id_vendedor);
//        Vendedor vendedor_native_2 = (Vendedor) vendedor_native.getSingleResult();
//        novoPrato.setVendedor(vendedor_native_2);
//
//       
//        em.persist(novoPrato);
//        em.flush();
//        
//        assertNotNull(novoPrato.getIdPrato());
//
//    }
//    
//    @Test
//    public void atualizarPratoNative() {
//        logger.info("Executando atualizarPrato()");
//
//        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
//        //bypassar cache do banco
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("id", 1);
//        Prato pratoUpdate = query.getSingleResult();
//        assertNotNull(pratoUpdate);
//        pratoUpdate.setNmPrato("Lasanha Bolonhesa");
//
//        em.flush();
//
//        Prato pratoAtual = query.getSingleResult();
//        assertEquals("Lasanha Bolonhesa", pratoAtual.getNmPrato());
//    }

}
