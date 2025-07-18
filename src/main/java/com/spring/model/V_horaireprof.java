 package com.spring.model;

 import jakarta.persistence.CascadeType;
 import jakarta.persistence.JoinColumn;
 import jakarta.persistence.OneToMany;
 import java.util.List;
 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.Table;
 import java.sql.Time;
import java.sql.Time;
import java.sql.Time;
import java.sql.Time;


 /*
    File generated By
    Christooj_Generation_Code
 */
 @Entity
 @Table(name = "V_horaireprof")
 public class V_horaireprof {
     @Id
     
     @Column(name = "row_num")
     Integer row_num;
     @Column(name = "idprof")
     Integer idprof;
     @Column(name = "matricule")
     String matricule;
     @Column(name = "jourid")
     Integer jourid;
     @Column(name = "jour")
     String jour;
     @Column(name = "heuredebut")
     Time heuredebut;
     @Column(name = "heurefin")
     Time heurefin;
     @Column(name = "idanneescolaire")
     Integer idanneescolaire;
     @Column(name = "academy")
     String academy;
     @Column(name = "adresse")
     String adresse;
     @Column(name = "classe")
     String classe;


     
    public Integer getRow_num(){
        return row_num;
    }


    public void setRow_num(Integer obj){
         row_num = obj;
    }


    public Integer getIdprof(){
        return idprof;
    }


    public void setIdprof(Integer obj){
         idprof = obj;
    }


    public String getMatricule(){
        return matricule;
    }


    public void setMatricule(String obj){
         matricule = obj;
    }


    public Integer getJourid(){
        return jourid;
    }


    public void setJourid(Integer obj){
         jourid = obj;
    }


    public String getJour(){
        return jour;
    }


    public void setJour(String obj){
         jour = obj;
    }


    public Time getHeuredebut(){
        return heuredebut;
    }


    public void setHeuredebut(Time obj){
         heuredebut = obj;
    }


    public Time getHeurefin(){
        return heurefin;
    }


    public void setHeurefin(Time obj){
         heurefin = obj;
    }


    public Integer getIdanneescolaire(){
        return idanneescolaire;
    }


    public void setIdanneescolaire(Integer obj){
         idanneescolaire = obj;
    }


    public String getAcademy(){
        return academy;
    }


    public void setAcademy(String obj){
         academy = obj;
    }


    public String getAdresse(){
        return adresse;
    }


    public void setAdresse(String obj){
         adresse = obj;
    }


    public String getClasse(){
        return classe;
    }


    public void setClasse(String obj){
         classe = obj;
    }



}

