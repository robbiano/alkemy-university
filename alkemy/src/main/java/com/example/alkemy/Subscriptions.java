package com.example.alkemy;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    private User student;


    //constructors
    public Subscriptions() {
    }

    public Subscriptions(Long id, Subject subject, User student) {
        this.id = id;
        this.subject = subject;
        this.student = student;
    }

    public Subscriptions(Subject subject, User student) {
        this.subject = subject;
        this.student = student;
    }


    //getters

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public User getStudent() {
        return student;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    //functions

    public void removeSubject(){
        this.subject = null;
    }
}
