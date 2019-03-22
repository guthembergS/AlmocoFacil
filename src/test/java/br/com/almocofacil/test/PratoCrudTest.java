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
        long id_vendedor = 1;
        Prato novoPrato = new Prato();
        novoPrato.setNmPrato("Lasanha de Bacalhau");
        novoPrato.setValor(15.00);

//        TypedQuery<Vendedor> query = em.createNamedQuery("Vendedor.PorId", Vendedor.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("id", 1);
//        Vendedor vendedor = query.getSingleResult();
//        assertNotNull(vendedor);
//        novoPrato.setVendedor(vendedor);

        Query vendedor_native = em.createNamedQuery("Vendedor.PorIdSQL");
        vendedor_native.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        vendedor_native.setParameter(1, id_vendedor);
        Vendedor vendedor_native_2 = (Vendedor) vendedor_native.getSingleResult();
        novoPrato.setVendedor(vendedor_native_2);

       
        em.persist(novoPrato);
        em.flush();
        
        assertNotNull(novoPrato.getIdPrato());

    }

    @Test
    public void atualizarPrato() {
        logger.info("Executando atualizarPrato()");

        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        //bypassar cache do banco
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 1);
        Prato pratoUpdate = query.getSingleResult();
        assertNotNull(pratoUpdate);
        pratoUpdate.setNmPrato("Lasanha Bolonhesa");
        
//        Query prato_query = em.createNamedQuery("Prato.PorNomeSQL", Prato.class);
//       prato_query.setParameter( 1, "Macarronada");
//       Prato prato_consulta = (Prato)prato_query.getSingleResult();

        em.flush();

        Prato pratoAtual = query.getSingleResult();
        assertEquals("Lasanha Bolonhesa", pratoAtual.getNmPrato());
    }

    @Test
    public void atualizarPratoMerge() {
        logger.info("Executando atualizarPratoMerge()");

        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 8);
        Prato prato = query.getSingleResult();
        assertNotNull(prato);
        prato.setNmPrato("Bacalhoada");
        em.clear();
        em.merge(prato);
        em.flush();
        assertEquals("Bacalhoada", query.getSingleResult().getNmPrato());

    }

    @Test
    public void removerPrato() {
        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorNome", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "sushi");
        Prato pratoRemov = query.getSingleResult();
        assertNotNull(pratoRemov);

        long idVendedor = pratoRemov.getVendedor().getIdUsuario();

        em.remove(pratoRemov);
        em.flush();

        TypedQuery<Prato> queryPratos = em.createNamedQuery("Prato.PorIdVendedor", Prato.class);
        queryPratos.setParameter("id", idVendedor);
        List<Prato> pratos = queryPratos.getResultList();

        boolean temSushi = false;

        for (int i = 0; i > pratos.size(); i++) {

            if (pratos.get(i).getNmPrato().equals("sushi")) {
                temSushi = true;
            }

        }

        assertEquals(temSushi, false);

    }

}
