package br.com.almocofacil.test;

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
    public void atualizarEnderecoEntrega() {
        
          TypedQuery<EnderecoEntrega> enderecoentrega = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
          enderecoentrega.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
          enderecoentrega.setParameter("id", 1);
          EnderecoEntrega enderecoEntrega = enderecoentrega.getSingleResult();
          assertNotNull(enderecoentrega);
        
           enderecoEntrega.setBairro("Caetes I");
           enderecoEntrega.setCep("53530785");
           enderecoEntrega.setCidade("Abreu e Lima");
           enderecoEntrega.setEstado("Pernambuco");
           enderecoEntrega.setLogadouro("Rua das amelias");   

    
            //em.merge(enderecoEntrega);
            em.flush();
     
        TypedQuery<EnderecoEntrega> enderecoteste = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        //queryCli.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoteste.setParameter("id", 1);
        
        EnderecoEntrega enderecoatualizado = enderecoteste.getSingleResult();
        
        assertEquals("53530785",enderecoatualizado.getCep());
        assertEquals("Pernambuco", enderecoatualizado.getEstado());
            
    }
    
    
    @Test
    public void atualizarClienteMerge() {
        
//        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("id", 2);
//        Cliente cliente = query.getSingleResult();
//        assertNotNull(cliente);
//        
//        String nome = "Thieago Antonio da Silva";
//        String email = "taoalu@gmail.com";
//        cliente.setNome(nome);
//        cliente.setEmail(email);
//        
//        em.clear();
//        em.merge(cliente);
//        em.flush();
//       
//        
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        cliente = query.getSingleResult();
//        assertEquals(nome, cliente.getNome());
//        assertEquals(email, cliente.getEmail());
    }

    
    @Test
    public void removerEnderecoEntrega() {
        
        TypedQuery<EnderecoEntrega> enderecoentrega = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        enderecoentrega.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoentrega.setParameter("id", 4);
        EnderecoEntrega endereco = enderecoentrega.getSingleResult();
        em.remove(endereco);
        em.flush();
        assertEquals(0,enderecoentrega.getResultList().size());
    }
    
}
