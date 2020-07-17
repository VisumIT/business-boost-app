package com.visumit.businessboost.model;

import java.util.ArrayList;

public class Company {

    private int id;
    private String address = null;
    private String publicPlace = null;
    private String number = null;
    private String uf = null;
    private String neighborhood = null;
    private String city = null;
    private String cep = null;
    private String site;
    ArrayList<Object> phones = new ArrayList<Object>();
    private String stateRegistration;
    private String companyName;
    private String cnpj;
    private String fictitiousName;
    private String email;
    private String description;
    private String logo;
    ArrayList<Object> profiles = new ArrayList<Object>();

    public Company(int id, String address, String publicPlace, String number, String uf, String neighborhood, String city, String cep, String site, ArrayList<Object> phones, String stateRegistration, String companyName, String cnpj, String fictitiousName, String email, String description, String logo, ArrayList<Object> profiles) {
        this.id = id;
        this.address = address;
        this.publicPlace = publicPlace;
        this.number = number;
        this.uf = uf;
        this.neighborhood = neighborhood;
        this.city = city;
        this.cep = cep;
        this.site = site;
        this.phones = phones;
        this.stateRegistration = stateRegistration;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.fictitiousName = fictitiousName;
        this.email = email;
        this.description = description;
        this.logo = logo;
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public ArrayList<Object> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Object> phones) {
        this.phones = phones;
    }

    public String getStateRegistration() {
        return stateRegistration;
    }

    public void setStateRegistration(String stateRegistration) {
        this.stateRegistration = stateRegistration;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFictitiousName() {
        return fictitiousName;
    }

    public void setFictitiousName(String fictitiousName) {
        this.fictitiousName = fictitiousName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<Object> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Object> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", publicPlace='" + publicPlace + '\'' +
                ", number='" + number + '\'' +
                ", uf='" + uf + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", cep='" + cep + '\'' +
                ", site='" + site + '\'' +
                ", phones=" + phones +
                ", stateRegistration='" + stateRegistration + '\'' +
                ", companyName='" + companyName + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", fictitiousName='" + fictitiousName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}