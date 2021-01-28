package com.example.alkemy;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private String name;
    private String lastName;
    private Long dni;
    private Boolean active;


    @OneToMany(mappedBy="teacher", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public Teacher(){

    }

    public Teacher(Long id, String name, String lastName, Long dni, Boolean active ){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.active = active;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDni() {
        return dni;
    }

    public Boolean getActive() {
        return active;
    }



    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
    }
}
