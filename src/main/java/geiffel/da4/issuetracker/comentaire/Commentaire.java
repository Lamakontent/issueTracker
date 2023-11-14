package geiffel.da4.issuetracker.comentaire;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Commentaire {
    @Id
    @NotNull
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User author;
    @ManyToOne
    @JoinColumn(referencedColumnName = "code")


    private Issue issue;
    private String contenu;

    public Commentaire() {
    }

    public Commentaire(Long id, User author, Issue issue, String contenu) {
        this.id = id;
        this.author = author;
        this.issue = issue;
        this.contenu = contenu;
        this.author.addCommentaire(this);
        this.issue.addCommentaire(this);
    }

    public Long getAuthorId(){
        return author.getId();
    }

    public Long getIssueCode(){
        return issue.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
