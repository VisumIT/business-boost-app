package com.visumit.businessboost.model;

import java.util.ArrayList;

public class RepresentanteResponse {

    private int id;
    ArrayList<Object> profiles = new ArrayList<Object>();
    ArrayList<Object> companies = new ArrayList<Object>();
    private String name;
    private String email;
    private String password;
    private String photograph;
    ArrayList<Object> phones = new ArrayList<Object>();
    private String cpf;
    private String dateOfBirth;
    private String gender;
    private String description;


    // Getter Methods

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotograph() {
        return photograph;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods

    public void setId( int id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public void setPhotograph( String photograph ) {
        this.photograph = photograph;
    }

    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public void setDateOfBirth( String dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RepresentanteResponse{" +
                "id=" + id +
                ", profiles=" + profiles +
                ", companies=" + companies +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photograph='" + photograph + '\'' +
                ", phones=" + phones +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
