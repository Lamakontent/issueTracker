package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IssueService {
    List<Issue> getAll();

    User getByCode(Long l);

    ResponseEntity<Object> update(Long l, Issue capture);

    Issue create(Issue any);

    ResponseEntity<Object> delete(Long capture);
}
