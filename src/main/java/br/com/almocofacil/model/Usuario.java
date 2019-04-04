package br.com.almocofacil.model;

import com.sun.org.glassfish.gmbal.NameValue;
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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Estratégia de herança.
@DiscriminatorColumn(name = "DISC_USUARIO", //Nome da coluna que vai discriminar subclasses.
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idUsuario;

    @NotNull
    @NameValue
    @Column(name = "nome")
    protected String nome;

    @NotNull
    @Size(max = 8)
    @Column(name = "senha")
    protected String senha;

    @NotNull
    @Email
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
