package br.com.almocofacil.test;

import br.com.almocofacil.model.CartaoCredito;
import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Prato;
import br.com.almocofacil.model.Vendedor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class GenericTest {

    protected static EntityManagerFactory emf;
    protected static Logger logger;
    protected EntityManager em;
    protected EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        //logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("almocofacil_01");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            fail(ex.getMessage());
        }
    }

    protected Date getData(Integer dia, Integer mes, Integer ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        return c.getTime();
    }
    
     protected Prato retornaPratoPorNome(String nmPrato){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.nmPrato = ?1",
                Prato.class);
        query.setParameter(1, nmPrato); //Setando parâmetro posicional.
        Prato prato = query.getSingleResult();
        return prato;
    }
     
    protected Prato retornaPrato(int id){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.idPrato = ?1",
                Prato.class);
        query.setParameter(1, id); //Setando parâmetro posicional.
        Prato prato = query.getSingleResult();
        return prato;
    }
    
    protected List<Prato> retornaPratosPorNome(String nmPrato){
        TypedQuery<Prato> query;
        query = em.createQuery(
                "SELECT c FROM Prato c WHERE c.nmPrato = ?1",
                Prato.class);
        query.setParameter(1, nmPrato); //Setando parâmetro posicional.
        List<Prato> pratos = query.getResultList();
        return pratos;
    }
    
    protected Vendedor retornaVendedor(int id){
        TypedQuery<Vendedor> query;
        query = em.createQuery(
                "SELECT c FROM Vendedor c WHERE c.idUsuario = ?1",
                Vendedor.class);
        query.setParameter(1, id); //Setando parâmetro posicional.
        Vendedor usu = query.getSingleResult();
        return usu;
    }
     protected Cliente retornaCliente(int id){
        TypedQuery<Cliente> query;
        query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.idUsuario = ?1",
                Cliente.class);
        query.setParameter(1, id); //Setando parâmetro posicional.
        Cliente usu = query.getSingleResult();
        return usu;
    }
     
     protected CartaoCredito retornaCartaoCredito(int id){
        TypedQuery<CartaoCredito> query;
        query = em.createQuery(
                "SELECT c FROM CartaoCredito c WHERE c.idCartaoCredito = ?1",
                CartaoCredito.class);
        query.setParameter(1, id); //Setando parâmetro posicional.
        CartaoCredito cartaocredito = query.getSingleResult();
        return cartaocredito;
    }
}
