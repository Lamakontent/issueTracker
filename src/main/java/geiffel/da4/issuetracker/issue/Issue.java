package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.TimestampUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Issue extends User {

    private Long code;
    private String title;
    private String content;
    private User emitter;
    private Timestamp dateCreated;
    private Timestamp dateClosed;

    public Issue(Long code, String title, String content, User emitter) {
        super();
        this.title = title;
        this.content = content;
        this.code = code;
        this.emitter = emitter;
        this.dateCreated = Timestamp.from(Instant.now());
        this.dateClosed = null;
    }

    public Issue() {
        super();

    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        boolean timestampEquals = TimestampUtils.isEquals(dateCreated, issue.dateCreated);
        timestampEquals = timestampEquals && TimestampUtils.isEquals(dateClosed, issue.dateClosed);
        return Objects.equals(code, issue.code) && Objects.equals(title, issue.title) && Objects.equals(content, issue.content) && timestampEquals;
    }

}
