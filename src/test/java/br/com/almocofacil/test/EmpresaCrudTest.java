package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import br.com.almocofacil.model.EnderecoEntrega;
import br.com.almocofacil.model.Prato;
import static br.com.almocofacil.test.GenericTest.logger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EmpresaCrudTest extends GenericTest {

    @Test
    public void criarEmpresa() {
        logger.info("Executando persistirEmpresa()");
        Empresa novaEmpresa = new Empresa();
        
        Long id = 4L;
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        novaEmpresa.setCnpj("1209039283298");
        novaEmpresa.setNmEmpresa("MV Sistemas");
        novaEmpresa.setTelefone("8133221122");

        TypedQuery<EnderecoEntrega> query = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", id);
        enderecoEntrega = query.getSingleResult();

        novaEmpresa.setEnderecoEntrega(enderecoEntrega);

        em.persist(novaEmpresa);
        em.flush();

        assertNotNull(novaEmpresa.getIdEmpresa());
    }

    @Test
    public void atualizaEmpresa() {
        logger.info("Executando atualizarEmpresa()");

        Empresa empresa = new Empresa();

        String cnpj = "123526378293827";
        String nome = "IFPE";
        Long id = 3L;

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", id);
        empresa = query.getSingleResult();

        assertNotNull(empresa);

        empresa.setCnpj(cnpj);
        empresa.setNmEmpresa(nome);

        em.flush();

        assertEquals(cnpj, empresa.getCnpj());
        assertEquals(nome, empresa.getNmEmpresa());

    }

    @Test
    public void atualizarEmpresaMerge() {
        logger.info("Executando atualizarEmpresaMerge()");

        Empresa empresa = new Empresa();

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 1);
        empresa = query.getSingleResult();

        assertNotNull(empresa);

        String telefone = "81995208867";
        String cnpj = "09283726172672";

        empresa.setCnpj(cnpj);
        empresa.setTelefone(telefone);

        em.clear();
        em.merge(empresa);
        em.flush();

        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        empresa = query.getSingleResult();

    }

    @Test
    public void removerEmpresa() {
        logger.info("Executando removerEmpresa()");
        
        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 4);
        Empresa empresa = query.getSingleResult();
        
        assertNotNull(empresa);

        em.remove(empresa);
        em.flush();

        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        assertEquals(0, query.getResultList().size());

    }
}
