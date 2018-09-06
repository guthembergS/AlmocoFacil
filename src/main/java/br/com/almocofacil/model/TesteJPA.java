package br.com.almocofacil.model;

import br.com.almocofacil.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TesteJPA {
        
       
    public static void main(String[] args){
        Usuario usuario = new Usuario();
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
    try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("almocofacil_01");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();
            em.persist(usuario);
            et.commit();
            
        } catch (Exception ex) {
            if (et != null)
                et.rollback();
            
        } 
          finally {
            if (em != null)
                em.close();       
            if (emf != null)
                emf.close();
        }
     }
}
