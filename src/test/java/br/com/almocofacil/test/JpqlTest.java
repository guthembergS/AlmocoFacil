package br.com.almocofacil.test;

import br.com.almocofacil.model.Cliente;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

public class JpqlTest extends GenericTest {

    @Test
    public void testaCliente() {
        logger.info("Executando busca de cliente()");
        TypedQuery<Cliente> query;
        query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.nome like ?1",
                Cliente.class);
        query.setParameter(1, "gerson brandao"); //Setando parâmetro posicional.
        Cliente usu = query.getSingleResult();
        
        assertEquals("gerson brandao", usu.getNome());
        
    }

}
