package br.com.almocofacil.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED) //Estratégia de herança.
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable{
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
    
}
