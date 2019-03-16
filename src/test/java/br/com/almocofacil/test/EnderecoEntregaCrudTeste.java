package br.com.almocofacil.test;

import br.com.almocofacil.model.EnderecoEntrega;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EnderecoEntregaCrudTeste extends GenericTest {

    @Test
    public void criarEnderecoEntrega() {
        logger.info("Executando criarEnderecoEntrega()");

        String bairro = "Caetes I";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";

        EnderecoEntrega endereco = new EnderecoEntrega();
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLogadouro(logradouro);

        em.persist(endereco);
        em.flush();

        assertNotNull(endereco.getId());

    }

    @Test
    public void atualizarEnderecoEntrega() {
        logger.info("Executando atualizarEnderecoEntrega()");

        String bairro = "Caetes II";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";
        long idEndereco = 1;

        TypedQuery<EnderecoEntrega> enderecoentrega = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        enderecoentrega.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoentrega.setParameter("id", idEndereco);
        EnderecoEntrega enderecoEntrega = enderecoentrega.getSingleResult();

        assertNotNull(enderecoentrega);

        enderecoEntrega.setBairro(bairro);
        enderecoEntrega.setCep(cep);
        enderecoEntrega.setCidade(cidade);
        enderecoEntrega.setEstado(estado);
        enderecoEntrega.setLogadouro(logradouro);

        em.flush();

        //Executa a query para confirmar a atualização
        EnderecoEntrega enderecoatualizado = enderecoentrega.getSingleResult();

        assertEquals(bairro, enderecoatualizado.getBairro());
        assertEquals(estado, enderecoatualizado.getEstado());
        assertEquals(cidade, enderecoatualizado.getCidade());
        assertEquals(cep, enderecoatualizado.getCep());
        assertEquals(logradouro, enderecoatualizado.getLogadouro());

    }

    @Test
    public void atualizarEnderecoEntregaMerge() {
        logger.info("Executando atualizarEnderecoEntregaMerge()");

        String bairro = "Caetes II";
        String cep = "53530785";
        String cidade = "Abreu e Lima";
        String estado = "Pernambuco";
        String logradouro = "Rua das amelias";
        long idEndereco = 1;

        TypedQuery<EnderecoEntrega> enderecoentrega = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        enderecoentrega.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoentrega.setParameter("id", idEndereco);
        EnderecoEntrega enderecoEntrega = enderecoentrega.getSingleResult();

        assertNotNull(enderecoentrega);

        enderecoEntrega.setBairro(bairro);
        enderecoEntrega.setCep(cep);
        enderecoEntrega.setCidade(cidade);
        enderecoEntrega.setEstado(estado);
        enderecoEntrega.setLogadouro(logradouro);

        em.clear();
        em.merge(enderecoEntrega);
        em.flush();
       
        //Executa a query para confirmar a atualização
        EnderecoEntrega enderecoatualizado = enderecoentrega.getSingleResult();

        assertEquals(bairro, enderecoatualizado.getBairro());
        assertEquals(estado, enderecoatualizado.getEstado());
        assertEquals(cidade, enderecoatualizado.getCidade());
        assertEquals(cep, enderecoatualizado.getCep());
        assertEquals(logradouro, enderecoatualizado.getLogadouro());

    }

    @Test
    public void removerEnderecoEntrega() {
        logger.info("Executando removerEnderecoEntrega()");

        TypedQuery<EnderecoEntrega> enderecoentrega = em.createNamedQuery("EnderecoEntrega.PorId", EnderecoEntrega.class);
        enderecoentrega.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        enderecoentrega.setParameter("id", 4);
        EnderecoEntrega endereco = enderecoentrega.getSingleResult();
        
        em.remove(endereco);
        em.flush();
        
        assertEquals(0, enderecoentrega.getResultList().size());
        
    }

}
