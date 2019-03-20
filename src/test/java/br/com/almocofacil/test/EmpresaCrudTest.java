package br.com.almocofacil.test;

import br.com.almocofacil.model.Empresa;
import br.com.almocofacil.model.EnderecoEntrega;
import static br.com.almocofacil.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EmpresaCrudTest extends GenericTest {

    @Test
    public void criarEmpresa() {
        logger.info("Executando criarEmpresa()");

        String cnpj = "12090392832983";
        String empresa = "MV Sistemas";
        String telefone = "8133221122";

        long idEndereco = 4;

        Empresa novaEmpresa = new Empresa();
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        novaEmpresa.setCnpj(cnpj);
        novaEmpresa.setNmEmpresa(empresa);
        novaEmpresa.setTelefone(telefone);

        TypedQuery<EnderecoEntrega> query = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idEndereco);
        enderecoEntrega = query.getSingleResult();

        novaEmpresa.setEnderecoEntrega(enderecoEntrega);

        em.persist(novaEmpresa);
        em.flush();

        assertNotNull(novaEmpresa.getIdEmpresa());
    }

    @Test
    public void atualizarEmpresa() {
        logger.info("Executando atualizarEmpresa()");

        String cnpj = "12352637829382";
        String nome = "IFPE";
        long idEmpresa = 1;

        Empresa empresa = new Empresa();

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idEmpresa);
        empresa = query.getSingleResult();

        assertNotNull(empresa);

        empresa.setCnpj(cnpj);
        empresa.setNmEmpresa(nome);

        em.flush();

        //Executa a query para confirmar a atualização
        empresa = query.getSingleResult();

        assertEquals(cnpj, empresa.getCnpj());
        assertEquals(nome, empresa.getNmEmpresa());

    }

    @Test
    public void atualizarEmpresaMerge() {
        logger.info("Executando atualizarEmpresaMerge()");

        String telefone = "81995208867";
        String cnpj = "09283726172672";
        long idEmpresa = 3;

        Empresa empresa = new Empresa();

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idEmpresa);
        empresa = query.getSingleResult();

        assertNotNull(empresa);

        empresa.setCnpj(cnpj);
        empresa.setTelefone(telefone);

        em.clear();
        em.merge(empresa);
        em.flush();

        //Executa a query para confirmar a atualização
        empresa = query.getSingleResult();

        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        empresa = query.getSingleResult();

    }

    @Test
    public void removerEmpresa() {
        logger.info("Executando removerEmpresa()");

        long idEmpresa = 4;

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idEmpresa);
        Empresa empresa = query.getSingleResult();

        assertNotNull(empresa);

        em.remove(empresa);
        em.flush();

        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        assertEquals(0, query.getResultList().size());

    }
}
