package geiffel.da4.issuetracker.user;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import geiffel.da4.issuetracker.comentaire.Commentaire;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @NotNull
    private Long id;

    private String nom;
    private Fonction fonction;
    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Commentaire> commentairesEcrits;

    public User(Long id, String nom, Fonction fonction) {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
        this.commentairesEcrits = new ArrayList<>();
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()){
            return false;
        }
        User comparing = (User) obj;
        return Objects.equals(this.id, comparing.getId()) &&
                this.nom.equals(comparing.getNom()) &&
                this.fonction == comparing.getFonction();
    }

   public void setCommentairesEcrits(List<Commentaire> commentairesEcrits) {
        this.commentairesEcrits = commentairesEcrits;
    }

    public List<Commentaire> getCommentairesEcrits(){
        return this.commentairesEcrits;
    }

    public void addCommentaire(Commentaire unCom){
        this.commentairesEcrits.add((unCom));
    }
}
