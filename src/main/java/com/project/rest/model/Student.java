package com.project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.util.internal.logging.InternalLogger;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "student",
                indexes = { @Index(name = "idx_nazwisko", columnList = "nazwisko", unique = false),
                @Index(name = "idx_nr_indeksu", columnList = "nr_indeksu", unique = true)} )
public class Student {
    @ManyToMany(mappedBy = "studenci")
    @JsonIgnoreProperties({"studenci"})
    private Set<Projekt> projekty;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Integer studentId;

    @NotNull(message = "Brak podanego adresu email")
    @Email(message = "Niepoprawny format adresu email")
    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 50, nullable = false)
    private String imie;

    @Column(length = 100, nullable = false)
    private String nazwisko;

    @Column(name = "nr_indeksu", length = 20, unique = true, nullable = false)
    private String nrIndeksu;


    @Column(nullable = false)
    private Boolean stacjonarny;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRole{
        return role;
    }

    public Boolean getStacjonarny() {
        return stacjonarny;
    }

    public void setStacjonarny(Boolean stacjonarny) {
        this.stacjonarny = stacjonarny;
    }

    public Set<Projekt> getProjekty() {
        return projekty;
    }

    public void setProjekty(Set<Projekt> projekty) {
        this.projekty = projekty;
    }

    public Student(){
    }

    public Student(String imie, String nazwisko, String nrIndeksu, Boolean stacjonarny){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.stacjonarny = stacjonarny;
    }

    public Student(String imie, String nazwisko, String nrIndeksu, String email, Boolean stacjonarny){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.email = email;
        this.stacjonarny = stacjonarny;
    }


}
