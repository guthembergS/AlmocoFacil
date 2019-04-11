/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.almocofacil.test;

import br.com.almocofacil.model.Prato;
import br.com.almocofacil.model.Vendedor;
import java.util.List;

/**
 *
 * @author guto
 */
public class ValidarPedido {
    
    
    public boolean validarVendedorPed(Vendedor vendedor, List<Prato> pratos){
        
        for(int p = 0;p<pratos.size();p++){
            if(vendedor != pratos.get(p).getVendedor())
                return false;
        }
        
        return true;
    }
    
    
}
