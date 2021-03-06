package br.com.almocofacil.test;

import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Pedido;
import br.com.almocofacil.model.Prato;
import static br.com.almocofacil.model.Prato_.idPrato;
import static br.com.almocofacil.test.GenericTest.logger;
import java.util.Date;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PedidoCrudTest extends GenericTest {

    @Test
    public void criarPedido() {
        logger.info("Executando criarPedido()");

        Pedido novoPedido = new Pedido();
        novoPedido.setDtPedido(getData(13, 9, 2018));
        long idPrato = 16;
        long idCliente = 8;
        
        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorId", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idPrato);
        Prato prato = query.getSingleResult();
        assertNotNull(prato);
        List<Prato> pratos = novoPedido.getPratos();

        //necessário colocar um TypedQuery já que na classe Pedido a coluna ID Cliente é (nullable = false)
        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
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

    @Test
    public void atualizarPedido() {
        logger.info("Executando atualizarPedido()");
        
        long idPedido = 13;

        TypedQuery<Pedido> query = em.createNamedQuery("Pedido.PorId", Pedido.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("id", idPedido);
        Pedido pedido = query.getSingleResult();
        assertNotNull(pedido);

        //alterando a data, diferente da data que foi persistida inicialmente
        Date dataPedido = getData(14, 9, 2018);

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", 5);
        Cliente cliente =(Cliente) queryCliente.getSingleResult();
        assertNotNull(cliente);

        //seta a nova data do pedido
        pedido.setDtPedido(dataPedido);
        //seta o retorno da query "Cliente.PorID"
        pedido.setCliente(cliente);
               
        em.flush();

        pedido = query.getSingleResult();

        assertEquals(dataPedido, pedido.getDtPedido());
        //testando apenas o ID Cliente com o ID Cliente do pedido
        assertEquals(cliente.getIdUsuario(), pedido.getCliente().getIdUsuario());
    }
    
    @Test
    public void atualizarPedidoMerge() {
        logger.info("Executando atualizarPedido()");

        long idPedido = 13;
        long idCliente = 5;
                
        TypedQuery<Pedido> queryPedido = em.createNamedQuery("Pedido.PorId", Pedido.class);
        queryPedido.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryPedido.setParameter("id", idPedido);
        Pedido pedido = queryPedido.getSingleResult();
        
        assertNotNull(pedido);
        
        Date dataPedido = getData(14, 9, 2018);

        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
        Cliente cliente = queryCliente.getSingleResult();
        
        assertNotNull(cliente);
       
        pedido.setDtPedido(dataPedido);      
        pedido.setCliente(cliente);
       
        em.clear();
        em.merge(pedido);
        em.flush();

        pedido = queryPedido.getSingleResult();

        assertEquals(dataPedido, pedido.getDtPedido());        
        assertEquals(cliente.getIdUsuario(), pedido.getCliente().getIdUsuario());
        
    }

    @Test
    public void removerPedido() {
        
        long idPedido = 19;

        TypedQuery<Pedido> queryRemove = em.createNamedQuery("Pedido.PorId", Pedido.class);
        queryRemove.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryRemove.setParameter("id", idPedido);
        Pedido pedido = queryRemove.getSingleResult();
        
        assertNotNull(pedido);

        em.remove(pedido);
        em.flush();

        assertEquals(0, queryRemove.getResultList().size());

    }        

    
    
    @Test
    public void criarPedidoNative() {
        logger.info("Executando criarPedido()");

        Pedido novoPedido = new Pedido();
        novoPedido.setDtPedido(getData(2,2, 2019));
        long idVendedor = 12;
        long idCliente = 8;
        double valorTotal = 0 ;
        
        TypedQuery<Prato> query = em.createNamedQuery("Prato.PorIdVendedorSQL", Prato.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, idVendedor);
        List<Prato> pratos = query.getResultList();
        assertNotNull(pratos);
        
        //adicionando pratos ao pedido
        for(int prato=0;prato<pratos.size();prato++){
            novoPedido.setPratos(pratos.get(prato));
            valorTotal += pratos.get(prato).getValor();
        }
        
        //adicionando prato sem ser do mesmo vendedor
//        TypedQuery<Prato> queryP = em.createNamedQuery("Prato.PorIdSQL", Prato.class);
//        queryP.setParameter(1, 16); //prato do vendedor 13
//        Prato prato = queryP.getSingleResult();        
//        novoPedido.setPratos(prato);
        
        //necessário colocar um TypedQuery já que na classe Pedido a coluna ID Cliente é (nullable = false)
        TypedQuery<Cliente> queryCliente = em.createNamedQuery("Cliente.PorId", Cliente.class);
        queryCliente.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryCliente.setParameter("id", idCliente);
        novoPedido.setCliente(queryCliente.getSingleResult());

        novoPedido.setVlTotal(valorTotal);
        novoPedido.setVendedor(pratos.get(0).getVendedor());
        
        ValidarPedido validacaoPedido = new ValidarPedido();
        
        assertEquals(validacaoPedido.validarVendedorPed(novoPedido.getVendedor(),novoPedido.getPratos()),true);
        
        em.persist(novoPedido);
        em.flush();
        assertNotNull(novoPedido.getIdPedido());

    }
    


    
}
