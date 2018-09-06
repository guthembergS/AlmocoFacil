package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author guthemberg
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idUsuario;
    
    @Column(name = "nome")
    protected String nome;
    
    @Column(name = "senha")
    protected String senha;
    
    @Column(name = "email")
    protected String email;
    
}
