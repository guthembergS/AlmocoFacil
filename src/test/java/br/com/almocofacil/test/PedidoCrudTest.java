package br.com.almocofacil.test;

import br.com.almocofacil.model.Pedido;
import br.com.almocofacil.model.Prato;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PedidoCrudTest extends GenericTest {

    @Test
    public void persistirPedido() {
        logger.info("Executando persistirPedido()");

        Pedido novoPedido = new Pedido();
        novoPedido.setDtPedido(getData(13, 9, 2018));

        List<Prato> pratosVendedor_1 = retornaPratosVendedor(1);
        List<Prato> pratosPedido = new ArrayList<Prato>();
        pratosPedido.add(pratosVendedor_1.get(2));
        pratosPedido.add(pratosVendedor_1.get(3));
        novoPedido.setPratos(pratosPedido);
        double somaPedido = 0.0;

        for (int i = 0; i < novoPedido.getPratos().size(); i++) {
            somaPedido += novoPedido.getPratos().get(i).getValor();
        }

        novoPedido.setVlTotal(somaPedido);
        novoPedido.setVendedor(retornaVendedor(1));
        novoPedido.setCliente(retornaCliente(1));

        em.persist(novoPedido);
        em.flush();

        assertNotNull(novoPedido.getIdPedido());
    }

    @Test
    public void atualizarPedido() {
        logger.info("Executando atualizarPedido()");       
        Pedido pedido = retornaPedido(1);
        assertNotNull(pedido);
        Date dataPedido = getData(14, 9,2018);
        pedido.setDtPedido(dataPedido);
        pedido.setCliente(retornaCliente(2));
        em.flush();
        pedido = retornaPedido(1);
        assertEquals(dataPedido, pedido.getDtPedido());
        assertEquals(retornaCliente(2),pedido.getCliente());
    }

    @Test
    public void atualizarPedidoMerge() {
       
        
    }
/*
    @Test
    public void removerCategoria() {
      
    }*/
}
