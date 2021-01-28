package com.example.alkemy;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;
    private String name;
    private LocalTime time;
    private Integer availability;

    @OneToMany(mappedBy="subject", fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    Set<Subscriptions> subscriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    public Subject(){

    }

    public Subject(Long id, String name, LocalTime time, Teacher teacher, Integer availability){
        this.id = id;
        this.name = name;
        this.time = time;
        this.teacher = teacher;
        this.availability = availability;
    }

    //getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public Integer getAvailability() {
        return availability;
    }

    public Set<Subscriptions> getSubscriptions() {
        return subscriptions;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public void setSubscriptions(Set<Subscriptions> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    //functions
    @PreRemove
    private void cleanRelations() {
        this.teacher.removeSubject(this);

        this.subscriptions.forEach(subscription -> {
            subscription.removeSubject();
        });
    }

}

