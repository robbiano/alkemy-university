package com.example.alkemy;

import java.time.LocalTime;

public class SubjectDTO {

    private Long id;
    private String name;
    private LocalTime time;
    private Integer availability;
    private String teacher;

    //constructors
    public SubjectDTO(){

    }

    public SubjectDTO( String name,LocalTime time, Teacher teacher, Integer availability){
        this.name = name;
        this.time = time;
        this.teacher = teacher.getName() +" "+ teacher.getLastName();
        this.availability = availability;
    }

    public SubjectDTO( String name,LocalTime time, Integer availability){
        this.name = name;
        this.time = time;
        this.availability = availability;
    }

    public SubjectDTO(Long id, String name,LocalTime time, Teacher teacher,  Integer availability){
        this.id = id;
        this.name = name;
        this.time = time;
        this.teacher = teacher.getName() +" "+ teacher.getLastName();
        this.availability = availability;
    }

    //getters

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public Integer getAvailability() {
        return availability;
    }

    public String getTeacher() {
        return teacher;
    }

    public Long getId() {
        return id;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
