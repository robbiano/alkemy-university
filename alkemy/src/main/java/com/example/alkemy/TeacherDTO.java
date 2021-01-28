package com.example.alkemy;

public class TeacherDTO {

    private Long id;
    private String name;
    private String lastName;
    private Long dni;
    private Boolean active;


    //constructores

    public TeacherDTO(){

    }

    public TeacherDTO(Teacher name, Teacher lastName, Teacher dni, Teacher active) {
        this.name = name.getName(); ;
        this.lastName = lastName.getLastName();
        this.dni = dni.getDni();
        this.active = active.getActive();

    }

    public TeacherDTO(Long id, Teacher name, Teacher lastName, Teacher dni, Teacher active) {
        this.id = id;
        this.name = name.getName();
        this.lastName = lastName.getLastName();
        this.dni = dni.getDni();
        this.active = active.getActive();

    }

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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


}
