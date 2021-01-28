package com.example.alkemy;

public class UserDTO {

    private Long id;
    private Integer file;
    private Long dni;

    //constructores

    public UserDTO() {
    }

    public UserDTO(Long id, Integer file, Long dni) {
        this.id = id;
        this.file = file;
        this.dni = dni;

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
}
