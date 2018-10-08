package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import br.com.almocofacil.model.EnderecoEntrega;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EnderecoEntregaCrudTeste extends GenericTest {
    
    @Test
    public void criarEnderecoEntrega() {
        
        EnderecoEntrega endereco = new EnderecoEntrega();
        endereco.setBairro("Caetes I");
        endereco.setCep("53530785");
        endereco.setCidade("Abreu e Lima");
        endereco.setEstado("Pernambuco");
        endereco.setLogadouro("Rua das amelias");
        
        em.persist(endereco);
        em.flush();

        assertNotNull(endereco.getId());
    }
    
    @Test
    public void atualizarCliente() {
        
        TypedQuery<Cliente> queryCliente= em.createNamedQuery("Cliente.PorId", Cliente.class);
        //queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", 1);
        Cliente cliente = queryCliente.getSingleResult();
        assertNotNull(cliente);
        
        cliente.setNome("Guthemberg Augusto de Souza");
        cliente.setEmail("guthemberg@outlook.com");
        cliente.setSenha("guthemberg123");
        
        //empresa
        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        //queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", 2);
        Empresa empresa = queryEmpresa.getSingleResult();
        assertNotNull(empresa);
        cliente.setEmpresa(empresa);
        
        em.merge(cliente);
        em.flush();
        
        TypedQuery<Cliente> queryCli = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        //queryCli.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCli.setParameter("nome", "Guthemberg Augusto de Souza");
        Cliente clienteAtualizado = queryCli.getSingleResult();
        assertEquals("guthemberg@outlook.com",clienteAtualizado.getEmail());
        assertEquals("guthemberg123",clienteAtualizado.getSenha());
        assertEquals(2,clienteAtualizado.getEmpresa().getIdEmpresa().longValue());    
    }
    
    
    @Test
    public void atualizarClienteMerge() {
        
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 2);
        Cliente cliente = query.getSingleResult();
        assertNotNull(cliente);
        
        String nome = "Thieago Antonio da Silva";
        String email = "taoalu@gmail.com";
        cliente.setNome(nome);
        cliente.setEmail(email);
        
        em.clear();
        em.merge(cliente);
        em.flush();
       
        
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        cliente = query.getSingleResult();
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
    }

    
    @Test
    public void removerCliente() {
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 10);
        Cliente cliente = query.getSingleResult();
        assertNotNull(cliente);
        
        em.remove(cliente);
        em.flush();
        
        assertEquals(0,query.getResultList().size());
    }
    
}
