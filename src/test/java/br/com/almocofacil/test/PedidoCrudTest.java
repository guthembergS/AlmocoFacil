package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Pedido;
import static br.com.almocofacil.model.Pedido_.cliente;
import br.com.almocofacil.model.Prato;
import br.com.almocofacil.model.Vendedor;
import static br.com.almocofacil.test.GenericTest.logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PedidoCrudTest extends GenericTest {

   
    @Test
    public void persistirPedido() {
        logger.info("Executando persistirPedido()");

        Pedido novoPedido = new Pedido();
        novoPedido.setDtPedido(getData(13, 9, 2018));
        //
        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", 16);
        Prato prato = query.getSingleResult();
        assertNotNull(prato);
        List<Prato>pratos= novoPedido.getPratos();
        
        //necessário colocar um TypedQuery já que na classe Pedido a coluna ID Cliente é (nullable = false)
        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", 8);
        novoPedido.setCliente(queryCliente.getSingleResult());
        
        novoPedido.setPratos(prato);
        novoPedido.setVlTotal(prato.getValor());
        //não foi necessário fazer um TypedQuery para o Vendedor, já que a classe Pedido tem um método [getVendedor()]
        //e foi necessário retornar o ID Vendedor pois ele tem a propriedade definida como (nullable = false)
        novoPedido.setVendedor(prato.getVendedor());
        //persistindo novoPedido no banco
        em.persist(novoPedido);
        //sincronizando os objetos do sistema com banco
        em.flush();
        //teste da persistencia do pedido (testando se o ID do pedido não é nulo)
        assertNotNull(novoPedido.getIdPedido());
        

    }
     
//    @Test
//    public void atualizarPedido() {
//        logger.info("Executando atualizarPedido()"); 
//        TypedQuery<Pedido> query = em.createNamedQuery("Pedido.PorId", Pedido.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("id", 7);
//        Pedido pedido = query.getSingleResult();
//        assertNotNull(pedido);
//       
//        Date dataPedido = getData(14, 9,2018);
//        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter("id", 7);
//        Cliente cliente = queryCliente.getSingleResult();
//        assertNotNull(cliente);
//        
//        pedido.setDtPedido(dataPedido);
//        pedido.setCliente(cliente);
//        
//       //pedido = retornaPedido(pedido);
//        
//        assertEquals(dataPedido, pedido.getDtPedido());
//        assertEquals(cliente, pedido.getCliente());
//        em.flush();
//    }
//    
//    @Test
//    public void atualizarPedidoMerge() {
//        logger.info("Executando atualizarPedidoMerge()");        
//        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.idPedido = ?1",Pedido.class);
//        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
//        query.setParameter(1, 18);
//        Pedido pedido = query.getSingleResult();
//        assertNotNull(pedido);
//        Cliente clienteAlteracao = retornaCliente(2);
//        pedido.setCliente(clienteAlteracao);
//        em.clear();        
//        em.merge(clienteAlteracao);
//        em.flush();
//        assertEquals(clienteAlteracao, query.getSingleResult().getCliente());
//    }
    /*
    @Test
    public void removerCategoria() {
      
    }*/
}
