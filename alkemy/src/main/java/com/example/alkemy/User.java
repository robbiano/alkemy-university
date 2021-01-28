package com.example.alkemy;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private Integer file;
    private Long dni;
    private String role;

    @OneToMany(mappedBy="student", fetch= FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Subscriptions> subscriptions = new HashSet<>();

    //constructores
    public User(){

    }

    public User(Long id, Integer file, Long dni, String role){
        this.id = id;
        this.file = file;
        this.dni = dni;
        this.role = role;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Integer getFile() {
        return file;
    }

    public Long getDni() {
        return dni;
    }

    public String getRole() {
        return role;
    }

    public Set<Subscriptions> getSubscriptions() {
        return subscriptions;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFile(Integer file) {
        this.file = file;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSubscriptions(Set<Subscriptions> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
