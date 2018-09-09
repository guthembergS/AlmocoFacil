package br.com.almocofacil.test;

import br.com.almocofacil.model.Usuario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class JpqlTest extends GenericTest {

    @Test
    public void testaUsuario() {
        logger.info("Executando busca de usuario()");
        TypedQuery<Usuario> query;
        query = em.createQuery(
                "SELECT c FROM Usuario c WHERE c.nome like ?1",
                Usuario.class);
        query.setParameter(1, "gerson andrade"); //Setando parâmetro posicional.
        Usuario usu = query.getSingleResult();

        assertEquals("gerson andrade", usu.getNome());
        
    }

}
