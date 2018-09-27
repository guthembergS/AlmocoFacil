package br.com.almocofacil.test;

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
        Cliente cliente = new Cliente();
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

        novaEmpresa.setCnpj("1209039283298");
        novaEmpresa.setNmEmpresa("MV Sistemas");
        novaEmpresa.setTelefone("8133221122");

        TypedQuery<EnderecoEntrega> query = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 2);
        enderecoEntrega = query.getSingleResult();

        TypedQuery<Cliente> queryC = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryC.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryC.setParameter("id", 9);
        cliente = queryC.getSingleResult();

        novaEmpresa.setClientes(cliente);
        novaEmpresa.setEnderecoEntrega(enderecoEntrega);

        em.persist(novaEmpresa);
        em.flush();

        assertNotNull(novaEmpresa.getIdEmpresa());

    }
       
    @Test
    public void atualizarEmpresa() {
        logger.info("Executando atualizarPratoMerge()");       
        TypedQuery<Empresa> query = em.createNamedQuery("CartaoCredito.PorId", Empresa.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 8);
        CartaoCredito cartaocredito = query.getSingleResult();
        assertNotNull(cartaocredito);
        String bandeira = "AmericanExpress";
        String numero = "0987890065488763";
        cartaocredito.setBandeira(bandeira);
        cartaocredito.setNumero(numero);

        em.clear();
        em.merge(cartaocredito);
        em.flush();
        
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cartaocredito = query.getSingleResult();

        assertEquals(bandeira, cartaocredito.getBandeira());
        assertEquals(numero, cartaocredito.getNumero());
    }

}
