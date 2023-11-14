package geiffel.da4.issuetracker.issue;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import geiffel.da4.issuetracker.comentaire.Commentaire;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.TimestampUtils;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
public class Issue {


    private String title;
    private String content;
    @Id
    private Long code;
    @OneToMany
    @JoinColumn(referencedColumnName = "code")
    private List<Commentaire> commentaires;

    private Timestamp dateClosed;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User emitter;
    private Timestamp dateCreated;

    public Issue(Long code, String title, String content, User emitter) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.emitter = emitter;
        this.dateCreated = Timestamp.from(Instant.now());
        this.dateClosed = null;
        this.commentaires = new ArrayList<>();
    }

    public Issue() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        Boolean timestampEquals = TimestampUtils.isEquals(dateCreated, issue.dateCreated);
        timestampEquals = timestampEquals && TimestampUtils.isEquals(dateClosed, issue.dateClosed);
        return Objects.equals(code, issue.code) && Objects.equals(title, issue.title) && Objects.equals(content, issue.content) && timestampEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, code, commentaires, dateClosed, emitter, dateCreated);
    }



    public void addCommentaire(Commentaire unCommentaire){
        commentaires.add(unCommentaire);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Timestamp getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Timestamp dateClosed) {
        this.dateClosed = dateClosed;
    }

    public User getEmitter() {
        return emitter;
    }

    public void setEmitter(User emitter) {
        this.emitter = emitter;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
