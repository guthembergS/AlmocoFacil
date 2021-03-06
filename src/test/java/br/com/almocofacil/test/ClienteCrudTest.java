 package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ClienteCrudTest extends GenericTest {

    @Test
    public void criarCliente() {
        logger.info("Executando criarCliente()");

        String nome = "Marcio Silva";
        String email = "marciosilva@gmail.com";
        String senha = "hhjskdj8";
        long idEmpresa = 1;
        long idCartaoCredito = 9;

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", idEmpresa);
        Empresa empresa = queryEmpresa.getSingleResult();

        assertNotNull(empresa);
        cliente.setEmpresa(empresa);

        TypedQuery<CartaoCredito> queryCartaoCredito = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        queryCartaoCredito.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCartaoCredito.setParameter("id", idCartaoCredito);
        CartaoCredito cartao = queryCartaoCredito.getSingleResult();

        assertNotNull(cartao);
        cliente.setCartaoCredito(cartao);

        em.persist(cliente);
        em.flush();

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("nome", nome);

        assertNotNull(queryCliente.getSingleResult());
    }

    @Test
    public void atualizarCliente() {
        logger.info("Executando atualizarCliente()");

        String novoNome = "Guthemberg Augusto de Souza";
        String novoEmail = "guthemberg@outlook.com";
        String novaSenha = "guthe123";
        long idCliente = 1;
        long idEmpresa = 2;

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
        Cliente cliente = queryCliente.getSingleResult();

        assertNotNull(cliente);

        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setSenha(novaSenha);

        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", idEmpresa);
        Empresa empresa = queryEmpresa.getSingleResult();

        assertNotNull(empresa);
        cliente.setEmpresa(empresa);

        em.flush();

        TypedQuery<Cliente> queryCli = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCli.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCli.setParameter("nome", novoNome);
        Cliente clienteAtualizado = queryCli.getSingleResult();

        assertEquals(novoEmail, clienteAtualizado.getEmail());
        assertEquals(novaSenha, clienteAtualizado.getSenha());
        assertEquals(2, clienteAtualizado.getEmpresa().getIdEmpresa().longValue());

    }

    @Test
    public void atualizarClienteMerge() {
        logger.info("Executando atualizarClienteMerge()");

        String novoNome = "Guthemberg Augusto de Souza";
        String novoEmail = "guthemberg@outlook.com";
        String novaSenha = "guthe433";
        long idCliente = 1;
        long idEmpresa = 2;
        long idCartaoCredito = 3;

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
        Cliente cliente = queryCliente.getSingleResult();

        assertNotNull(cliente);

        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setSenha(novaSenha);

        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", idEmpresa);
        Empresa empresa = queryEmpresa.getSingleResult();

        assertNotNull(empresa);
        cliente.setEmpresa(empresa);

        TypedQuery<CartaoCredito> queryCartao = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        queryCartao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCartao.setParameter("id", idCartaoCredito);
        CartaoCredito cartaoCredito = queryCartao.getSingleResult();

        assertNotNull(cartaoCredito);
        cliente.setCartaoCredito(cartaoCredito);

        em.clear();
        em.merge(cliente);
        em.flush();

        TypedQuery<Cliente> queryCli = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCli.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCli.setParameter("nome", novoNome);
        Cliente clienteAtualizado = queryCli.getSingleResult();

        assertEquals(novoEmail, clienteAtualizado.getEmail());
        assertEquals(novaSenha, clienteAtualizado.getSenha());
        assertEquals(2, clienteAtualizado.getEmpresa().getIdEmpresa().longValue());
        assertEquals(3, clienteAtualizado.getCartaoCredito().getIdCartaoCredito().longValue());

    }

    @Test
    public void removerCliente() {
        logger.info("Executando removerCliente()");

        long idCliente = 9;

        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorId", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idCliente);
        Cliente cliente = query.getSingleResult();

        assertNotNull(cliente);

        em.remove(cliente);
        em.flush();

        assertEquals(0, query.getResultList().size());

    }
    
    @Test
    public void criarClienteNative() {
        logger.info("Executando criarClienteNative()");

        String nome = "Antonio Filho";
        String email = "antonio.filho@mail.com";
        String senha = "123456";
        long idEmpresa = 1;
        long idCartaoCredito = 10;

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorIdSQL", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter(1, idEmpresa);
        Empresa empresa = queryEmpresa.getSingleResult();

        assertNotNull(empresa);
        cliente.setEmpresa(empresa);

        TypedQuery<CartaoCredito> queryCartaoCredito = em.createNamedQuery("CartaoCredito.PorIdSQL", CartaoCredito.class);
        queryCartaoCredito.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCartaoCredito.setParameter(1, idCartaoCredito);
        CartaoCredito cartao = queryCartaoCredito.getSingleResult();

        assertNotNull(cartao);
        cliente.setCartaoCredito(cartao);

        em.persist(cliente);
        em.flush();

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorNomeSQL", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter(1, nome);

        assertNotNull(queryCliente.getSingleResult());
    }
    
    @Test
    public void atualizarClienteNativeQueryId() {
        logger.info("Executando atualizarClienteNativeQueryId()");

        String novoNome = "Guthemberg Augusto de Souza";
        String novoEmail = "guthemberg@outlook.com";
        String novaSenha = "guthemberg123";
        long idCliente = 2;
        long idEmpresa = 2;

        Query clienteNativeQuery = em.createNamedQuery("Cliente.PorIdSQL");
        clienteNativeQuery.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        clienteNativeQuery.setParameter(1, idCliente);
        Cliente clienteNative = (Cliente) clienteNativeQuery.getSingleResult();

        assertNotNull(clienteNative);

        clienteNative.setNome(novoNome);
        clienteNative.setEmail(novoEmail);
        clienteNative.setSenha(novaSenha);

        Query empresaNativeQuery = em.createNamedQuery("Empresa.PorIdSQL");
        empresaNativeQuery.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        empresaNativeQuery.setParameter(1, idEmpresa);
        Empresa empresaNative = (Empresa) empresaNativeQuery.getSingleResult();

        assertNotNull(empresaNative);
        clienteNative.setEmpresa(empresaNative);

        em.flush();

        Cliente clienteAtualizado = (Cliente) clienteNativeQuery.getSingleResult();

        assertEquals(novoEmail, clienteAtualizado.getEmail());
        assertEquals(novaSenha, clienteAtualizado.getSenha());
        assertEquals(2, clienteAtualizado.getEmpresa().getIdEmpresa().longValue());

    }
    
    @Test
    public void atualizarClienteNativeMerge() {
        logger.info("Executando atualizarClienteNativeMerge()");

        String novoNome = "Nayara Souza";
        String novoEmail = "nayara.souza@outlook.com";
        String novaSenha = "010203";
        long idCliente = 7;
        long idEmpresa = 1;
        long idCartaoCredito = 3;

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
        Cliente cliente = queryCliente.getSingleResult();

        assertNotNull(cliente);

        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setSenha(novaSenha);

        TypedQuery<Empresa> queryEmpresa = em.createNamedQuery("Empresa.PorId", Empresa.class);
        queryEmpresa.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryEmpresa.setParameter("id", idEmpresa);
        Empresa empresa = queryEmpresa.getSingleResult();

        assertNotNull(empresa);
        cliente.setEmpresa(empresa);

        TypedQuery<CartaoCredito> queryCartao = em.createNamedQuery("CartaoCredito.PorId", CartaoCredito.class);
        queryCartao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCartao.setParameter("id", idCartaoCredito);
        CartaoCredito cartaoCredito = queryCartao.getSingleResult();

        assertNotNull(cartaoCredito);
        cliente.setCartaoCredito(cartaoCredito);

        em.clear();
        em.merge(cliente);
        em.flush();

        TypedQuery<Cliente> queryCli = em.createNamedQuery("Cliente.PorNome", Cliente.class);
        queryCli.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCli.setParameter("nome", novoNome);
        Cliente clienteAtualizado = queryCli.getSingleResult();

        assertEquals(novoEmail, clienteAtualizado.getEmail());
        assertEquals(novaSenha, clienteAtualizado.getSenha());
        assertEquals(idEmpresa, clienteAtualizado.getEmpresa().getIdEmpresa().longValue());
        assertEquals(idCartaoCredito, clienteAtualizado.getCartaoCredito().getIdCartaoCredito().longValue());

    }
     
    @Test
    public void removerClienteNative() {
        logger.info("Executando removerClienteNative()");

        long idCliente = 15;

        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.PorIdSQL", Cliente.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, idCliente);
        Cliente cliente = query.getSingleResult();
        assertNotNull(cliente);

        em.remove(cliente);
        em.flush();

        assertEquals(0, query.getResultList().size());

    }
}
