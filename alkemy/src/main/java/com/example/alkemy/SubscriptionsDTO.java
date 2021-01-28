package com.example.alkemy;

public class SubscriptionsDTO {

    private Long id;
    private String subject;
    private Long student;

    //constructors

    public SubscriptionsDTO() {
    }

    public SubscriptionsDTO(Long id, Subject subject, User student) {
        this.id = id;
        this.subject = subject.getName();
        this.student = student.getDni();
    }

    public SubscriptionsDTO(Subject subject, User student) {
        this.subject = subject.getName();
        this.student = student.getDni();
    }

    //getters

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public Long getStudent() {
        return student;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudent(Long student) {
        this.student = student;
    }
}
