package com.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commentaires")
public class Commentaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "programmescolaireid")
    private Integer programmescolaireid;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "datecreation")
    private LocalDateTime datecreation;

    @Column(name = "parent_comment_id")
    private Integer parentCommentId;

    @Transient
    private boolean lu;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", insertable = false, updatable = false)
    @JsonBackReference 
    private Commentaires parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference 
    private List<Commentaires> replies;

    public Commentaires() {
    }

    public Commentaires(Integer programmescolaireid, Integer userid,String nom,String prenom, String commentaire, LocalDateTime datecreation, Commentaires parentComment) {
        this.programmescolaireid = programmescolaireid;
        this.userid = userid;
        this.nom = nom;
        this.prenom = prenom;
        this.commentaire = commentaire;
        this.datecreation = datecreation;
        this.parentComment = parentComment;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProgrammescolaireid() {
        return programmescolaireid;
    }

    public void setProgrammescolaireid(Integer programmescolaireid) {
        this.programmescolaireid = programmescolaireid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Commentaires getParentComment() {
        return parentComment;
    }

    public void setParentComment(Commentaires parentComment) {
        this.parentComment = parentComment;
    }

    public List<Commentaires> getReplies() {
        return replies;
    }

    public void setReplies(List<Commentaires> replies) {
        this.replies = replies;
    }
}
