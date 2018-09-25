package br.com.almocofacil.test;

import br.com.almocofacil.model.Cliente;
import br.com.almocofacil.model.Empresa;
import br.com.almocofacil.model.Prato;
import static br.com.almocofacil.test.GenericTest.logger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EmpresaCrudTest extends GenericTest {
     
    
    @Test
    public void persistirEmpresa() {
        logger.info("Executando persistirEmpresa()");
        Empresa novaEmpresa = new Empresa();
        novaEmpresa.setCnpj("1209039283298");
        novaEmpresa.setNmEmpresa("Avanade");
        novaEmpresa.setTelefone("8133221122");
        //endereco criado pelo dataset
        novaEmpresa.setEnderecoEntrega(retornaEndereco(4));
        
        Cliente cliente = retornaCliente(8);
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(cliente);
        novaEmpresa.setClientes(clientes);
        
        em.persist(novaEmpresa);
        em.flush();
        assertNotNull(novaEmpresa.getIdEmpresa());
    }
    
    @Test
    public void atualizarEmpresa() {
        logger.info("Executando atualizarEmpresa()");       
        Empresa empresaUpdate = retornaEmpresa(3);
        assertNotNull(empresaUpdate);
        String nomeEmpresa = "Motorola";
        String cnpjEmpresa = "12345678901234";
        empresaUpdate.setNmEmpresa(nomeEmpresa);
        empresaUpdate.setCnpj(cnpjEmpresa);
        em.flush();
        Empresa empresaAtual = retornaEmpresa(4);
        assertEquals(nomeEmpresa, empresaAtual.getNmEmpresa());
        assertEquals(cnpjEmpresa, empresaAtual.getCnpj());
    }
    
    @Test
    public void removerEmpresa() {
        Empresa empresaRemov = retornaEmpresa(4);
        assertNotNull(empresaRemov);
        em.remove(empresaRemov);
        em.flush();
        assertNotNull(retornaEmpresa(4));
        
    }
    
    
    
}
