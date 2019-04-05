package br.com.almocofacil.test;

import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import br.com.almocofacil.model.EnderecoEntrega;
import static br.com.almocofacil.test.GenericTest.logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EmpresaCrudTest extends GenericTest {

    @Test
    public void criarEmpresa() {
        logger.info("Executando criarEmpresa()");

        String cnpj = "45594214000171";
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

        String cnpj = "59807258000147";
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
        String cnpj = "27173184000148";
        long idEmpresa = 3;

        Empresa empresa = new Empresa();

        TypedQuery<Empresa> query = em.createNamedQuery("Empresa.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idEmpresa);
        empresa = query.getSingleResult();

        assertNotNull(empresa);

        empresa.setCnpj(cnpj);
        empresa.setTelefone(telefone);
        //limpa o entity manager (deixa de gerenciar o objeto)
        em.clear();
        //força o gerencimento do objeto
        em.merge(empresa);
        //sincroniza com o banco de dados
        em.flush();

        //Executa a query para confirmar a atualização
        empresa = query.getSingleResult();

        assertEquals(cnpj, empresa.getCnpj());
        assertEquals(telefone, empresa.getTelefone());

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

    @Test
    public void atualizarEmpresaNativeQueryId() {
        logger.info("Executando atualizarEmpresaNativeQueryId()");

        String cnpj = "20350679000156";
        String nome = "IFPE";
        long idEmpresa = 1;

        Empresa empresa = new Empresa();

        Query empresaNativeQuery = em.createNamedQuery("Empresa.PorIdSQL");
        empresaNativeQuery.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        empresaNativeQuery.setParameter(1, idEmpresa);
        Empresa empresaNative = (Empresa) empresaNativeQuery.getSingleResult();

        assertNotNull(empresaNative);

        empresaNative.setCnpj(cnpj);
        empresaNative.setNmEmpresa(nome);

        em.flush();

        //Executa a query para confirmar a atualização
        empresaNativeQuery.getSingleResult();

        assertEquals(cnpj, empresaNative.getCnpj());
        assertEquals(nome, empresaNative.getNmEmpresa());

    }

}
