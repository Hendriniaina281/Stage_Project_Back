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
 

 /*
    File generated By
    Christooj_Generation_Code
 */
 @Entity
 @Table(name = "V_salaireprof")
 public class V_salaireprof {
     @Id
     
     @Column(name = "row_num")
     Integer row_num;
     @Column(name = "idsalaireprof")
     Integer idsalaireprof;
    @Column(name = "idecole")
     Integer idecole;
     @Column(name = "classe")
     String classe;
     @Column(name = "salaire")
     Double salaire;

          
     public Integer getIdsalaireprof() {
        return idsalaireprof;
    }


    public void setIdsalaireprof(Integer idsalaireprof) {
        this.idsalaireprof = idsalaireprof;
    }


     
    public Integer getRow_num(){
        return row_num;
    }


    public void setRow_num(Integer obj){
         row_num = obj;
    }


    public Integer getIdecole(){
        return idecole;
    }


    public void setIdecole(Integer obj){
         idecole = obj;
    }


    public String getClasse(){
        return classe;
    }


    public void setClasse(String obj){
         classe = obj;
    }


    public Double getSalaire(){
        return salaire;
    }


    public void setSalaire(Double obj){
         salaire = obj;
    }



}

