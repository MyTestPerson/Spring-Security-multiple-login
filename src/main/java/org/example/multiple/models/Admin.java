package org.example.multiple.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NaturalId
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    public Admin() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
