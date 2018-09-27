package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ClienteCrudTest extends GenericTest {
    
    @Test
    public void criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Marcio Silva");
        cliente.setEmail("marcio.silva@gmail.com");
        cliente.setSenha("senha123");
        
        //empresa
        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", 1);
        Empresa empresa = queryEmpresa.getSingleResult();
        assertNotNull(empresa);
        cliente.setEmpresa(empresa);
        
        //CartaoCredito
        TypedQuery<CartaoCredito> queryCartao = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        queryCartao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCartao.setParameter("id", 9);
        CartaoCredito cartao = queryCartao.getSingleResult();
        assertNotNull(cartao);
        cliente.setCartaoCredito(cartao);
        
        em.persist(cliente);
        em.flush();
        
        TypedQuery<Cliente> queryCat = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCat.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCat.setParameter("nome", "Marcio Silva");
        assertNotNull(queryCat.getSingleResult());
    }
    
    @Test
    public void atualizarCliente() {
        
        TypedQuery<Cliente> queryCliente= em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", 1);
        Cliente cliente = queryCliente.getSingleResult();
        assertNotNull(cliente);
        
        cliente.setNome("Guthemberg Augusto de Souza");
        cliente.setEmail("guthemberg@outlook.com");
        cliente.setSenha("guthemberg123");
        
        //empresa
        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", 2);
        Empresa empresa = queryEmpresa.getSingleResult();
        assertNotNull(empresa);
        cliente.setEmpresa(empresa);
        
        em.flush();
        
        TypedQuery<Cliente> queryCat = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCat.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCat.setParameter("nome", "Guthemberg Augusto de Souza");
        Cliente clienteAtualizado = queryCat.getSingleResult();
        assertEquals("guthemberg@outlook.com",clienteAtualizado.getEmail());
        assertEquals("guthemberg123",clienteAtualizado.getSenha());
        assertEquals(2,clienteAtualizado.getEmpresa().getIdEmpresa().longValue());    
    }
    
    

    
    
}
